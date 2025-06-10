package com.PrismaMobi.Prisma_Mobi.entities;

import com.PrismaMobi.Prisma_Mobi.entities.enums.RideStatus;

import java.time.LocalDateTime;

public record RideStartDTO(Long id, String driverName, String passengerName,
                           LocalDateTime rideStartDate, RideStatus rideStatus) {

    public RideStartDTO(Ride ride) {
        this(ride.getId(),
            ride.getDriver().getName(),
            ride.getPassenger().getName(),
            ride.getRideStartDate(),
            ride.getRideStatus());
    }
}
