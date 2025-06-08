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

    public Vehicle(@Valid VehicleDTO vehicle) {
        this.brand = vehicle.brand();
        this.model = vehicle.model();
        this.year = vehicle.year();
        this.plate = vehicle.plate();
    }

    public void update(@Valid VehicleDTO vehicle) {
        if(vehicle.brand() != null && !vehicle.brand().isBlank()){
            this.brand = vehicle.brand();
        }
        if(vehicle.model() != null && !vehicle.model().isBlank()){
            this.model = vehicle.model();
        }
        if(vehicle.year() != null && !vehicle.year().isBlank()){
            this.year = vehicle.year();
        }
        if(vehicle.plate() != null && !vehicle.plate().isBlank()){
            this.plate = vehicle.plate().toUpperCase();
        }
    }
}
