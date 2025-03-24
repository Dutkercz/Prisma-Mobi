package com.PrismaMobi.Prisma_Mobi.entities.vehicle;

import com.PrismaMobi.Prisma_Mobi.entities.driver.Driver;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Table
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    @Column(name = "manufacture_year")
    private String year;
    private String plate;

    public Vehicle(@NotNull @Valid VehicleDTO vehicle) {
        this.id = null;
        this.brand = vehicle.brand();
        this.model = vehicle.model();
        this.year = vehicle.year();
        this.plate = vehicle.plate();
    }
}
