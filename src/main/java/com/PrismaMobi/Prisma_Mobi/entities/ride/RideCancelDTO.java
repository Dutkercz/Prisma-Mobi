package com.PrismaMobi.Prisma_Mobi.entities.ride;

import com.PrismaMobi.Prisma_Mobi.entities.enums.CancelledBy;
import com.PrismaMobi.Prisma_Mobi.entities.enums.Roles;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;

import java.time.LocalDateTime;

public record RideCancelDTO(Long id, String passengerName, String driverName, CancelledBy canceledBy, LocalDateTime rideFinishDate, String rideComment) {

    public RideCancelDTO(Ride ride) {
        this(ride.getId(), ride.getPassenger().getName(), ride.getDriver() != null ? ride.getDriver().getName() : "Sem Motorista",
                ride.getCancelledBy(), ride.getRideFinishDate(), ride.getRideComment());
    }
}
