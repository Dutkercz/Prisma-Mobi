package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.Ride;
import com.PrismaMobi.Prisma_Mobi.entities.RideCoordinates;
import com.PrismaMobi.Prisma_Mobi.entities.address.Destination;
import com.PrismaMobi.Prisma_Mobi.entities.address.Origin;
import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
import com.PrismaMobi.Prisma_Mobi.repositories.DriverRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.PassengerRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.RideRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.UsersRepository;
import com.PrismaMobi.Prisma_Mobi.services.utils.RidePrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private DriverRepository driverRepository;



    public Ride saveRide(RideCoordinates coordinates, String login) {
        Users users = usersRepository.findByLogin(login);

        Passenger passenger = passengerRepository.findPassengerByUsersId(users.getId())
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));

        System.out.println("Selecionando Motora aleatorio");
        Driver driver = driverRepository.findRandomDriver();
        System.out.println("Motora selecionado: " + driver);

        Ride ride = rideRepository.save(new Ride(null,
                new Origin(coordinates.originLat(), coordinates.originLongi()),
                new Destination(coordinates.destinationLat(), coordinates.destinationLongi()),
                RidePrice.ridePrice(coordinates), LocalDateTime.now(), passenger, driver ));

        return ride;
    }

}
