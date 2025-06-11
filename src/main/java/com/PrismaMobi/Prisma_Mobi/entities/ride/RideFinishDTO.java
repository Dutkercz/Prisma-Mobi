package com.PrismaMobi.Prisma_Mobi.entities.ride;

import com.PrismaMobi.Prisma_Mobi.entities.enums.RideStatus;

import java.time.LocalDateTime;

public record RideFinishDTO(Long id, String driverName, String passengerName,
                            LocalDateTime rideFinishDate, Double totalPrice, RideStatus rideStatus) {

    public RideFinishDTO(Ride ride) {
        this(ride.getId(),
                ride.getDriver().getName(),
                ride.getPassenger().getName(),
                ride.getRideFinishDate(),
                ride.getTotalPrice(),
                ride.getRideStatus());
    }
}
