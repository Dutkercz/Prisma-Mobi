package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverRegisterDTO;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
import com.PrismaMobi.Prisma_Mobi.entities.vehicle.Vehicle;
import com.PrismaMobi.Prisma_Mobi.repositories.DriverRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    public Driver saveDriver(DriverRegisterDTO driverRegisterDTO, String login) {

        Users users = usersRepository.findByLogin(login);

        return driverRepository.save(new Driver(null, driverRegisterDTO.name(), driverRegisterDTO.cpf(), true,
                driverRegisterDTO.gender(),new Vehicle(driverRegisterDTO.vehicle()), users));
    }

    public DriverRegisterDTO findByPlate(String plate) {
        var driver = driverRepository.findDriverByVehiclePlate(plate.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        return new DriverRegisterDTO(driver);
    }

    @Transactional
    public Driver deleteDriver(String login) {
        Users users = usersRepository.findByLogin(login);
        Driver driver = driverRepository.findByUsersIdAndActive(users.getId(), true)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found."));
        driver.setInactive();
        return driver;
    }
}
