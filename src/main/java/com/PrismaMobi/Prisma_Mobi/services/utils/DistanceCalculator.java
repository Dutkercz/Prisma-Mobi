package com.PrismaMobi.Prisma_Mobi.services.utils;

public class DistanceCalculator {

    private static final double EARTH_RADIUS_KM = 6370.0;

    public static double calculateDistance(double lat1, double longi1, double lat2, double longi2) {
        double deltaLatRadians = Math.toRadians(lat2 - lat1);
        double deltaLongiRadians = Math.toRadians(longi2 - longi1);

        double lat1Radians = Math.toRadians(lat1);
        double lat2Radians = Math.toRadians(lat2);

        double haversineA = Math.sin(deltaLatRadians / 2) * Math.sin(deltaLatRadians / 2) +
                Math.cos(lat1Radians) * Math.cos(lat2Radians) *
                        Math.sin(deltaLongiRadians / 2) * Math.sin(deltaLongiRadians / 2);

        double haversineC = 2 * Math.atan2(Math.sqrt(haversineA), Math.sqrt(1 - haversineA));

        return EARTH_RADIUS_KM * haversineC;
    }
}
