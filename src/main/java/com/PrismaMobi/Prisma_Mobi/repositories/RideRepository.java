package com.PrismaMobi.Prisma_Mobi.repositories;

import com.PrismaMobi.Prisma_Mobi.entities.enums.RideStatus;
import com.PrismaMobi.Prisma_Mobi.entities.ride.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride, Long> {

    Optional<Ride> findByIdAndRideStatus(Long id, RideStatus rideStatus);

    Page<Ride> findAllByPassengerId(Long id, Pageable pageable);

    Page<Ride> findAllByDriverId(Long id, Pageable pageable);

    List<Ride> findAllByDriverIdAndRideStatusAndRideFinishDateBetween(Long id, RideStatus rideStatus, LocalDateTime startDate, LocalDateTime endDate);
}
