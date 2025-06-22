package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.address.Destination;
import com.PrismaMobi.Prisma_Mobi.entities.address.Origin;
import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverMonthlyReport;
import com.PrismaMobi.Prisma_Mobi.entities.driver.DriverRidesDTO;
import com.PrismaMobi.Prisma_Mobi.entities.enums.RideStatus;
import com.PrismaMobi.Prisma_Mobi.entities.enums.Roles;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.PassengerRidesDTO;
import com.PrismaMobi.Prisma_Mobi.entities.ride.*;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
import com.PrismaMobi.Prisma_Mobi.repositories.DriverRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.PassengerRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.RideRepository;
import com.PrismaMobi.Prisma_Mobi.repositories.UsersRepository;
import com.PrismaMobi.Prisma_Mobi.services.utils.RidePrice;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideService {


    private final RideRepository rideRepository;
    private final UsersRepository usersRepository;
    private final PassengerRepository passengerRepository;
    private final DriverValidationService driverValidationService;

    public RideService(RideRepository rideRepository, UsersRepository usersRepository, PassengerRepository passengerRepository, DriverRepository driverRepository, DriverValidationService driverValidationService) {
        this.rideRepository = rideRepository;
        this.usersRepository = usersRepository;
        this.passengerRepository = passengerRepository;
        this.driverValidationService = driverValidationService;
    }

    @Transactional
    public RideDetails saveNewRide(RideCoordinates coordinates, String login) {
        Users user = usersRepository.findByLogin(login);

        Passenger passenger = passengerRepository.findPassengerByUsersId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Passageiro não encontrado"));

        if (user.getRoles() == Roles.ROLE_PASSENGER){
            Ride ride = rideRepository.save(new Ride(null,
                    new Origin(coordinates.originLat(), coordinates.originLongi()),
                    new Destination(coordinates.destinationLat(), coordinates.destinationLongi()),
                    RidePrice.ridePrice(coordinates),
                    LocalDateTime.now(),null, null, null, null,
                    passenger, null, RideStatus.REQUESTED, null));
            return new RideDetails(ride);
        }
        throw new RuntimeException("Usuário não tem permissões para esta ação");


    }

    @Transactional
    public RideAcceptedResponse acceptRide(String login, Long id) {
        Ride ride = rideRepository.findByIdAndRideStatus(id, RideStatus.REQUESTED)
                .orElseThrow(() -> new EntityNotFoundException("Esta viagem não está disponível."));

        Driver driver = driverValidationService.getValidateDriver(login);

        ride.acceptBy(driver);
        return new RideAcceptedResponse(ride);
    }

    @Transactional
    public RideStartDTO startRide(String login, Long id) {
        Ride ride = rideRepository.findByIdAndRideStatus(id, RideStatus.ACCEPTED)
                .orElseThrow(() -> new EntityNotFoundException("Esta viagem não está disponível."));

        Driver driver = driverValidationService.getValidateDriver(login);

        if (!ride.getDriver().equals(driver)){
            throw new AccessDeniedException("Usuário não tem permissões para esta ação");
        }
        ride.startRide();
        return new RideStartDTO(ride);
    }

    @Transactional
    public RideFinishDTO finishRide(String login, Long id) {
        Ride ride = rideRepository.findByIdAndRideStatus(id, RideStatus.IN_PROGRESS)
                .orElseThrow(() -> new EntityNotFoundException("Esta viagem não está disponível."));

        Driver driver = driverValidationService.getValidateDriver(login);

        if (!ride.getDriver().equals(driver)){
            throw new AccessDeniedException("Usuário não tem permissões para esta ação");
        }
        ride.finishRide();
        return new RideFinishDTO(ride);
    }

    @Transactional
    public RideCancelDTO cancelRide(String login, Long id, String comment) {
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Esta viagem não está disponível."));

        if (ride.getRideStatus() != RideStatus.REQUESTED && ride.getRideStatus() != RideStatus.ACCEPTED){
            throw new RuntimeException("Esta viagem não atende aos requisitos para cancelamento.");
        }

        Users user = usersRepository.findByLogin(login);

        Users motorista = ride.getDriver() != null ? ride.getDriver().getUsers() : null;
        Users passageiro = ride.getPassenger() != null ? ride.getPassenger().getUsers() : null;

        if(user != passageiro && user != motorista) {
            throw new AccessDeniedException("Você não tem permissão para cancelar esta viagem.");
        }

        ride.canceledRide(comment, user);
        return new RideCancelDTO(ride);
    }

    /**
     * Busca todas as Rides de um passageiro autenticado, separando por páginas com o Pageable
     *
     * @param login usuário autenticado
     * @param pageable suporte a paginação
     * @return retona o Objeto já convertido em DTO contendo informações necessárias
     */
    public Page<PassengerRidesDTO> findAllPassengerRides(String login, Pageable pageable) {
        Users user = usersRepository.findByLogin(login);
        if (user.getRoles() != Roles.ROLE_PASSENGER){
            throw new AccessDeniedException("Usuário não tem permissões para esta ação");
        }
        Passenger passenger = passengerRepository.findPassengerByUsersId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Passageiro não encontrado"));

        return rideRepository.findAllByPassengerId(passenger.getId(), pageable)
                .map(PassengerRidesDTO::new);
    }

    /**
     * Busca todas a Rides de um Driver, tambem com suporte a paginação
     * @param login usuário autenticado
     * @param pageable suporte a paginação
     * @return retorna o Objeto já convertido em DTO com as informações necessárias
     */
    public Page<DriverRidesDTO> findALlDriverRides(String login, Pageable pageable) {
        Driver driver = driverValidationService.getValidateDriver(login);
        return rideRepository.findAllByDriverId(driver.getId(), pageable).map(DriverRidesDTO::new);
    }

    public DriverMonthlyReport getDriverMonthlyReport(String login, int year, int month) {
        Driver driver = driverValidationService.getValidateDriver(login);
        LocalDateTime startDate = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime endDate = startDate.plusMonths(1);
        List<Ride> rides = rideRepository
                .findAllByDriverIdAndRideStatusAndRideFinishDateBetween(driver.getId(),
                                                                    RideStatus.FINISHED,
                                                                    startDate, endDate);
        double totalEarns = rides.stream()
                .map(Ride::getTotalPrice)
                .reduce(0.0, Double::sum);

        return new DriverMonthlyReport(driver.getId(), driver.getName(), String.valueOf(month +" - "+ year), rides.size(), totalEarns);

    }

    public RideDetails getActiveRide(String login) {
        Users user = usersRepository.findByLogin(login);

        if(user.getRoles().equals(Roles.ROLE_PASSENGER)){
            Passenger passenger = passengerRepository.findPassengerByUsersId(user.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Passageiro não encontrado"));
            Ride ride = rideRepository.findByPassengerIdAndRideStatus(passenger.getId(), RideStatus.IN_PROGRESS);
            if (ride != null) {
                return new RideDetails(ride);
            }
        } else if (user.getRoles().equals(Roles.ROLE_DRIVER)) {
            Driver driver = driverValidationService.getValidateDriver(login);
            Ride ride = rideRepository.findByDriverIdAndRideStatus(driver.getId(), RideStatus.IN_PROGRESS);
            if (ride != null) {
                return new RideDetails(ride);
            }
        }
        throw new EntityNotFoundException("Nenhuma corrida em andamento para este usuário.");
    }
}
