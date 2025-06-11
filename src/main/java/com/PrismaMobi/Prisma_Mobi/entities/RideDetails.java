package com.PrismaMobi.Prisma_Mobi.entities;

import java.time.LocalDateTime;

public record RideDetails(Long rideId, Long passengerId, LocalDateTime rideRequestDate, Double totalPrice, Double[] origin, Double[] destination) {

    public RideDetails(Ride ride) {
        this(ride.getId(),
            ride.getPassenger().getId(),
            ride.getRideRequestDate(),
            ride.getTotalPrice(),
            new Double[]{ride.getOrigin().getLatitudeOrigin(), ride.getOrigin().getLongitudeOrigin()},
            new Double[]{ride.getDestination().getLatitudeDestination(), ride.getDestination().getLongitudeDestination()});
    }
}
