package com.PrismaMobi.Prisma_Mobi.entities;

import java.time.LocalDateTime;

public record RideDetails(String passenger, String driver, LocalDateTime date, Double totalPrice) {
    public RideDetails(Ride ride) {
        this(ride.getPassenger().getName(),
                ride.getDriver().getName(),
                ride.getRideDate(),
                ride.getTotalPrice());
    }
}
