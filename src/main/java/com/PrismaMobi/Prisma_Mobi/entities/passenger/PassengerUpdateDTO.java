package com.PrismaMobi.Prisma_Mobi.entities.passenger;

import jakarta.validation.constraints.NotBlank;

public record PassengerUpdateDTO(
        @NotBlank(message = "O campo 'name' não pode estar em branco.")
        String name) {
}
