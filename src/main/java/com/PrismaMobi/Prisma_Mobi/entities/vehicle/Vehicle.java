package com.PrismaMobi.Prisma_Mobi.entities.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class Vehicle {

    private String brand;
    private String model;
    @Column(name = "manufacture_year")
    private String year;
    private String plate;

    public Vehicle(@NotNull @Valid VehicleDTO vehicle) {
        this.brand = vehicle.brand();
        this.model = vehicle.model();
        this.year = vehicle.year();
        this.plate = vehicle.plate();
    }
}
