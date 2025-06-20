package com.PrismaMobi.Prisma_Mobi.entities.passenger;

import com.PrismaMobi.Prisma_Mobi.entities.ride.Ride;

import java.time.LocalDateTime;

public record PassengerRidesDTO(Long rideId, String passengerName, String driverName,
                                Double totalPrice, LocalDateTime finishRideDate) {

    public PassengerRidesDTO(Ride ride) {
        this(ride.getId(),
            ride.getPassenger().getName(),
            ride.getDriver() != null ? ride.getDriver().getName() : "No Driver",
            ride.getTotalPrice(),
            ride.getRideFinishDate());
    }
}
