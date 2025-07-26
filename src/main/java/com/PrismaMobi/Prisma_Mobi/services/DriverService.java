package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverDTO;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverRegisterDTO;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverUpdateDTO;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
import com.PrismaMobi.Prisma_Mobi.entities.vehicle.Vehicle;
import com.PrismaMobi.Prisma_Mobi.repositories.DriverRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Driver driver = new Driver(null, driverRegisterDTO.name(), driverRegisterDTO.cpf(), true,
                driverRegisterDTO.gender(), new Vehicle(driverRegisterDTO.vehicle()), users);
        return driverRepository.save(driver);
    }

    public DriverDTO findByPlate(String plate) {
        Driver driver = driverRepository.findDriverByVehicleCarPlate(plate.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        return new DriverDTO(driver);
    }

    @Transactional
    public void deleteDriver(String login) {
        Users users = usersRepository.findByLogin(login);
        Driver driver = driverRepository.findByUsersIdAndActive(users.getId(), true)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found."));
        driver.setInactive();
    }

    public Page<Driver> findAll(Pageable pageable) {
        return driverRepository.findAllByActiveTrue(pageable);
    }

    @Transactional
    public Driver updateDriver(DriverUpdateDTO updateDTO, String login) {
        Users users = usersRepository.findByLogin(login);
        System.out.println(users.getUsername());
        Driver driver = driverRepository.findByUsersIdAndActive(users.getId(), true)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found."));
        System.out.println("Driver nome atual: " + driver.getName());
        driver.update(updateDTO);
        return driver;
    }
}
