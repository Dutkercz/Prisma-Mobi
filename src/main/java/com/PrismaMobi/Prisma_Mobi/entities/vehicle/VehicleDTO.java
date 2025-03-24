package com.PrismaMobi.Prisma_Mobi.entities.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VehicleDTO(
        @NotBlank(message = "O campo BRAND é obrigatório")
        String brand,

        @NotBlank(message = "O campo MODEL é obrigatório")
        String model,

        @NotBlank(message = "O campo YEAR é obrigatório")
        @Pattern(regexp = "\\d{4}", message = "O campo YEAR deve ser no formato yyyy")
        String year,

        @NotBlank(message = "O campo PLATE é obrigatório")
        String plate){
}
