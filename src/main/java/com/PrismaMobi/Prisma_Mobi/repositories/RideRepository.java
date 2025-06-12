package com.PrismaMobi.Prisma_Mobi.repositories;

import com.PrismaMobi.Prisma_Mobi.entities.passenger.Passenger;
import com.PrismaMobi.Prisma_Mobi.entities.ride.Ride;
import com.PrismaMobi.Prisma_Mobi.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride, Long> {

    Optional<Ride> findByIdAndRideStatus(Long id, RideStatus rideStatus);

    Page<Ride> findAllByPassengerId(Long id, Pageable pageable);
}
