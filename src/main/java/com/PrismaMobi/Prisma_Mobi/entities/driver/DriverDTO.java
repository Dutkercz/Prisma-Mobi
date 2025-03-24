package com.PrismaMobi.Prisma_Mobi.entities.driver;

import com.PrismaMobi.Prisma_Mobi.entities.enums.Gender;
import com.PrismaMobi.Prisma_Mobi.entities.passenger.PassengerDTO;
import com.PrismaMobi.Prisma_Mobi.entities.vehicle.Vehicle;
import com.PrismaMobi.Prisma_Mobi.entities.vehicle.VehicleDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DriverDTO (@NotBlank(message = "O campo NOME não pode estar em branco.")
                         String name,

                         @NotBlank(message = "O campo CPF não pode estar em branco.")
                         @Pattern(regexp = "\\d{11}", message = "O campo CPF deve conter 11 digitos")
                         String cpf,

                         @NotNull
                         Gender gender,

                         @NotNull @Valid VehicleDTO vehicle){

    public DriverDTO(Driver driver) {
        this(driver.getName(), "*********-"+driver.getCpf().substring(9,11), driver.getGender(), new VehicleDTO(driver.getVehicle()));
    }

    public DriverDTO listing() {
        return new DriverDTO(name,"*********-"+cpf.substring(9,11), gender, vehicle);
    }
}
