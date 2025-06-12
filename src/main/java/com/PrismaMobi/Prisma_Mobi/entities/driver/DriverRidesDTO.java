package com.PrismaMobi.Prisma_Mobi.entities.driver;

import com.PrismaMobi.Prisma_Mobi.entities.ride.Ride;

import java.time.LocalDateTime;

public record DriverRidesDTO(Long rideId, String driverName, String passengerName,
                             Double totalPrice, LocalDateTime finishRideDate) {

    public DriverRidesDTO(Ride ride) {
        this(ride.getId(),
            ride.getDriver().getName(),
            ride.getPassenger().getName(),
            ride.getTotalPrice(),
            ride.getRideFinishDate()
            );
    }
}
