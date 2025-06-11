package com.PrismaMobi.Prisma_Mobi.entities.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class Vehicle {

    private String carBrand;
    private String carModel;
    @Column(name = "manufacture_year")
    private String year;
    private String carPlate;

    public Vehicle(@Valid VehicleDTO vehicle) {
        this.carBrand = vehicle.brand();
        this.carModel = vehicle.model();
        this.year = vehicle.year();
        this.carPlate = vehicle.plate();
    }

    public void update(@Valid VehicleDTO vehicle) {
        if(vehicle.brand() != null && !vehicle.brand().isBlank()){
            this.carBrand = vehicle.brand();
        }
        if(vehicle.model() != null && !vehicle.model().isBlank()){
            this.carModel = vehicle.model();
        }
        if(vehicle.year() != null && !vehicle.year().isBlank()){
            this.year = vehicle.year();
        }
        if(vehicle.plate() != null && !vehicle.plate().isBlank()){
            this.carPlate = vehicle.plate().toUpperCase();
        }
    }
}
