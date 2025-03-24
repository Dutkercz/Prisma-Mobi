package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverDTO;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
import com.PrismaMobi.Prisma_Mobi.entities.vehicle.Vehicle;
import com.PrismaMobi.Prisma_Mobi.respositories.DriverRepository;
import com.PrismaMobi.Prisma_Mobi.respositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private VehicleService vehicleService;

    @Transactional
    public Driver saveDriver(DriverDTO driverDTO, String login) {

        Users users = usersRepository.findByLogin(login);
        Vehicle vehicle = vehicleService.saveVehicle(driverDTO.vehicle());

        return driverRepository.save(new Driver(null, driverDTO.name(), driverDTO.cpf(), true,
                driverDTO.gender(),vehicle, users));
    }
}
