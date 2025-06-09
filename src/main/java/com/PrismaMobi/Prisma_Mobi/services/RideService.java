package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.Ride;
import com.PrismaMobi.Prisma_Mobi.entities.RideCoordinates;
import com.PrismaMobi.Prisma_Mobi.entities.address.Destination;
import com.PrismaMobi.Prisma_Mobi.entities.address.Origin;
import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.enums.RideStatus;
import com.PrismaMobi.Prisma_Mobi.entities.enums.Roles;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
import com.PrismaMobi.Prisma_Mobi.repositories.DriverRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.PassengerRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.RideRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.UsersRepository;
import com.PrismaMobi.Prisma_Mobi.services.utils.RidePrice;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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

    @Transactional
    public Ride saveNewRide(RideCoordinates coordinates, String login) {
        Users users = usersRepository.findByLogin(login);

        Passenger passenger = passengerRepository.findPassengerByUsersId(users.getId())
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));

        if (passenger.getUsers().getRoles() == Roles.ROLE_PASSENGER){
            return rideRepository.save(new Ride(null,
                    new Origin(coordinates.originLat(), coordinates.originLongi()),
                    new Destination(coordinates.destinationLat(), coordinates.destinationLongi()),
                    RidePrice.ridePrice(coordinates), LocalDateTime.now(), passenger, null, RideStatus.REQUESTED));
        }
        throw new RuntimeException("Usuário não tem permissões para esta ação");


    }

    @Transactional
    public Ride acceptRide(String login, Long id) {
        Ride ride = rideRepository.findByIdAndRideStatus(id, RideStatus.REQUESTED)
                .orElseThrow(() -> new EntityNotFoundException("Esta viagem não está disponível."));

        Users users = usersRepository.findByLogin(login);

        Driver driver = driverRepository.findByUsersIdAndActive(users.getId(), true)
                .orElseThrow(() -> new EntityNotFoundException("Motorista não encontrado"));

        if (driver.getUsers().getRoles() == Roles.ROLE_DRIVER){
            ride.acceptBy(driver);
            return ride;
        }
        throw new AccessDeniedException("Usuário não tem permissões para esta ação");

    }
}
