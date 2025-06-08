package com.PrismaMobi.Prisma_Mobi.entities.passenger;

import com.PrismaMobi.Prisma_Mobi.entities.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record PassengerRequestDTO(
        @NotBlank(message = "O campo nome não pode estar em branco.")
        String name,

        @NotNull(message = "O campo nome não pode estar em branco.")
        @Pattern(regexp = "\\d{11}", message = "Deve ter conter 11 dígitos.")
        String cpf,

        @NotNull
        Gender gender
) {
}
