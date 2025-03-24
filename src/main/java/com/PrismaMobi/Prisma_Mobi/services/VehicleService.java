package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.vehicle.Vehicle;
import com.PrismaMobi.Prisma_Mobi.entities.vehicle.VehicleDTO;
import com.PrismaMobi.Prisma_Mobi.respositories.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public Vehicle saveVehicle(VehicleDTO vehicleDTO){
        return vehicleRepository.save(new Vehicle(vehicleDTO));
    }
}
