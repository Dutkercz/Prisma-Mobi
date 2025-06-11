package com.PrismaMobi.Prisma_Mobi.entities.ride;

import com.PrismaMobi.Prisma_Mobi.entities.enums.Roles;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;

import java.time.LocalDateTime;

public record RideCancelDTO(Long id, String passengerName, String driverName, Roles canceledBy, LocalDateTime rideFinishDate, String rideComment) {

    public RideCancelDTO(Ride ride, Users users) {
        this(ride.getId(), ride.getPassenger().getName(), ride.getDriver().getName() != null ? ride.getDriver().getName() : "Sem Motorista",
                users.getRoles(), ride.getRideFinishDate(), ride.getRideComment());
    }
}
