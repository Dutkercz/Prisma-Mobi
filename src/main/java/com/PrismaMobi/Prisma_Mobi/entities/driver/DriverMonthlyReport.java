package com.PrismaMobi.Prisma_Mobi.entities.driver;

import com.PrismaMobi.Prisma_Mobi.entities.ride.Ride;

import java.util.List;

public record DriverMonthlyReport(Long id, String driverName, String date,
                                  Integer totalRides, Double totalEarned) {

}
