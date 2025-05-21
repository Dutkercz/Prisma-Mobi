package com.PrismaMobi.Prisma_Mobi.entities.driver;

import com.PrismaMobi.Prisma_Mobi.entities.vehicle.VehicleDTO;

public record DriverDTO(
        Long id,
        String name,
        VehicleDTO vehicleDTO
) {
    public DriverDTO(Driver driver) {
        this(driver.getId(), driver.getName(), new VehicleDTO(driver.getVehicle()));
    }
}
