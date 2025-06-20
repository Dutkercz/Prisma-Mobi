package com.PrismaMobi.Prisma_Mobi.services.utils;

import com.PrismaMobi.Prisma_Mobi.entities.ride.RideCoordinates;

public class RidePrice {

    private static final double RIDE_FEE = 15.0;
    private static final double RIDE_BASE_DISTANCE = 3.0;
    private static final double RIDE_EXTRA_FEE = 5.0; //TAXA ADICIONAL POR KM

    /*Se a distância total for de até 3Km, será cobrado somente a taxa da corrida de R$ 15,00,
    mas se a distância for superior a 3Km, será cobrado um extra de R$ 5,00 a mais por Km.
    */
    public static Double ridePrice(RideCoordinates coordinates) {
        double rideTotalDistance = DistanceCalculator.calculateDistance(coordinates.originLat(), coordinates.originLongi(),
                coordinates.destinationLat(), coordinates.destinationLongi());
        if (rideTotalDistance <= RIDE_BASE_DISTANCE) {
            return RIDE_FEE;
        }
        return RIDE_FEE + (RIDE_EXTRA_FEE * (rideTotalDistance - RIDE_BASE_DISTANCE));

    }
}
