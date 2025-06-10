package com.PrismaMobi.Prisma_Mobi.entities;

import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import com.PrismaMobi.Prisma_Mobi.entities.enums.RideStatus;

import java.time.LocalDateTime;

public record RideAcceptedResponse(Long passengerId,
                                   Long rideId,
                                   LocalDateTime rideAcceptDate,
                                   Double totalPrice,
                                   Double[] origin, Double[] destination,
                                   RideStatus status,
                                   String driver) {

    public RideAcceptedResponse(Ride ride) {
        this(ride.getPassenger().getId(), ride.getId(), ride.getRideAcceptDate(), ride.getTotalPrice(),
                new Double[]{ride.getOrigin().getLatitudeOrigin(), ride.getOrigin().getLongitudeOrigin()},
                new Double[]{ride.getDestination().getLatitudeDestination(), ride.getDestination().getLongitudeDestination()},
                ride.getRideStatus(), ride.getDriver().getName());
    }
}
