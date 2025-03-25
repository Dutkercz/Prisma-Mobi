package com.PrismaMobi.Prisma_Mobi.entities;

import com.PrismaMobi.Prisma_Mobi.entities.address.Destination;
import com.PrismaMobi.Prisma_Mobi.entities.address.Origin;
import jakarta.validation.constraints.NotNull;

public record RideCoordinates(@NotNull Double originLat, @NotNull Double originLongi,
                              @NotNull Double destinationLat , @NotNull Double destinationLongi
                      ) {
}
