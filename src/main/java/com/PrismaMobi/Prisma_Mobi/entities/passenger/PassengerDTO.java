package com.PrismaMobi.Prisma_Mobi.entities.passenger;

import com.PrismaMobi.Prisma_Mobi.entities.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PassengerDTO(@NotBlank String name,
                           @NotNull @Pattern(regexp = "\\d{11}")String cpf,
                           @NotNull Gender gender){
    public PassengerDTO listing(){
        return new PassengerDTO(name,"*********-"+cpf.substring(9,11), gender);
    }

}
