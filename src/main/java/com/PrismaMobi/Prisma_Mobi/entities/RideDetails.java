package com.PrismaMobi.Prisma_Mobi.entities;

import java.time.LocalDateTime;

public record RideDetails(Long passengerId, LocalDateTime date, Double totalPrice, Double[] origin, Double[] destination) {
    public RideDetails(Ride ride) {
        this(ride.getPassenger().getId(),
                ride.getRideDate(),
                ride.getTotalPrice(),
                new Double[]{ride.getOrigin().getLatitudeOrigin(), ride.getOrigin().getLongitudeOrigin()},
                new Double[]{ride.getDestination().getLatitudeDestination(), ride.getDestination().getLongitudeDestination()});
    }
}
