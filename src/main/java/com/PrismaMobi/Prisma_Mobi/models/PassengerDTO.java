package com.PrismaMobi.Prisma_Mobi.models;

import com.PrismaMobi.Prisma_Mobi.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PassengerDTO(@NotNull String name,
                           @NotNull @JsonIgnore String cpf,
                           @NotBlank Gender gender){

}
