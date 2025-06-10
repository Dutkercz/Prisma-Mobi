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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RideService {


    private final RideRepository rideRepository;
    private final UsersRepository usersRepository;
    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;
    private final DriverValidationService driverValidationService;

    public RideService(RideRepository rideRepository, UsersRepository usersRepository, PassengerRepository passengerRepository, DriverRepository driverRepository, DriverValidationService driverValidationService) {
        this.rideRepository = rideRepository;
        this.usersRepository = usersRepository;
        this.passengerRepository = passengerRepository;
        this.driverRepository = driverRepository;
        this.driverValidationService = driverValidationService;
    }

    @Transactional
    public Ride saveNewRide(RideCoordinates coordinates, String login) {
        Users users = usersRepository.findByLogin(login);

        Passenger passenger = passengerRepository.findPassengerByUsersId(users.getId())
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));

        if (passenger.getUsers().getRoles() == Roles.ROLE_PASSENGER){
            return rideRepository.save(new Ride(null,
                    new Origin(coordinates.originLat(), coordinates.originLongi()),
                    new Destination(coordinates.destinationLat(), coordinates.destinationLongi()),
                    RidePrice.ridePrice(coordinates),
                    LocalDateTime.now(),null, null, null,
                    passenger, null, RideStatus.REQUESTED));
        }
        throw new RuntimeException("Usuário não tem permissões para esta ação");


    }

    @Transactional
    public Ride acceptRide(String login, Long id) {
        Ride ride = rideRepository.findByIdAndRideStatus(id, RideStatus.REQUESTED)
                .orElseThrow(() -> new EntityNotFoundException("Esta viagem não está disponível."));

        Driver driver = driverValidationService.getValidateDriver(login);

        if (!ride.getDriver().equals(driver)){
            throw new AccessDeniedException("Usuário não tem permissões para esta ação");
        }
        ride.startBy(driver);
        return ride;
    }

    @Transactional
    public Ride startRide(String login, Long id) {
        Ride ride = rideRepository.findByIdAndRideStatus(id, RideStatus.ACCEPTED)
                .orElseThrow(() -> new EntityNotFoundException("Esta viagem não está disponível."));

        Driver driver = driverValidationService.getValidateDriver(login);

        if (!ride.getDriver().equals(driver)){
            throw new AccessDeniedException("Usuário não tem permissões para esta ação");
        }
        ride.startBy(driver);
        return ride;
    }

    @Transactional
    public Ride finishRide(String login, Long id) {
        Ride ride = rideRepository.findByIdAndRideStatus(id, RideStatus.IN_PROGRESS)
                .orElseThrow(() -> new EntityNotFoundException("Esta viagem não está disponível."));

        Driver driver = driverValidationService.getValidateDriver(login);

        if (!ride.getDriver().equals(driver)){
            throw new AccessDeniedException("Usuário não tem permissões para esta ação");
        }
        ride.finishBy(driver);
        return ride;
    }
}
