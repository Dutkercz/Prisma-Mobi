package com.PrismaMobi.Prisma_Mobi.entities.driver;

import com.PrismaMobi.Prisma_Mobi.entities.vehicle.VehicleDTO;

public record DriverUpdateDTO(String name, VehicleDTO vehicle) {
}
