package com.PrismaMobi.Prisma_Mobi.entities;

import jakarta.validation.constraints.NotBlank;

public record UpdatePassengerDTO(@NotBlank(message = "O campo 'name' não pode estar em branco.")
                        String name) {
}
