package com.PrismaMobi.Prisma_Mobi.services.utils;

import com.PrismaMobi.Prisma_Mobi.entities.RideCoordinates;

public class RidePrice {

    private static final double RIDE_FEE = 15.0;

    /*Se a distância total for de até 3Km, será cobrado somente a taxa da corrida de R$ 15,00,
    mas se a distância for superior a 3Km, será cobrado um extra de R$ 5,00 a mais por Km.
    */
    public static Double ridePrice(RideCoordinates coordinates) {
        double distance = DistanceCalculator.calculateDistance(coordinates.originLat(), coordinates.originLongi(),
                coordinates.destinationLat(), coordinates.destinationLongi());
        if (distance <= 3.00) {
            System.out.println("Total sem add" + RIDE_FEE);
            return RIDE_FEE;
        }
        return RIDE_FEE + (5 * (distance - 3.0));

    }
}
