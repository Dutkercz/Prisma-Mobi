package com.PrismaMobi.Prisma_Mobi.entities;

import jakarta.validation.constraints.NotNull;

public record RideCoordinates(@NotNull Double originLat, @NotNull Double originLongi,
                              @NotNull Double destinationLat, @NotNull Double destinationLongi
) {
}
