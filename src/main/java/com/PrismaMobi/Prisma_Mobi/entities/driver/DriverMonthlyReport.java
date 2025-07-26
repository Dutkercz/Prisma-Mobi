package com.PrismaMobi.Prisma_Mobi.entities.driver;

public record DriverMonthlyReport(Long id, String driverName, String date,
                                  Integer totalRides, Double totalEarned) {

}
