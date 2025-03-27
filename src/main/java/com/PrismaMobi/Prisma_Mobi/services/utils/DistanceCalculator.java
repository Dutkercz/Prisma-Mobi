package com.PrismaMobi.Prisma_Mobi.services.utils;

public class DistanceCalculator{

    private static final double EARTH_RADIUS = 6370.0;

    public static double calculateDistance(double lat1, double longi1, double lat2, double longi2){
        double latDistance = Math.toRadians(lat2 - lat1);
        double longiDistance = Math.toRadians(longi2 - longi1);
        double a = Math.sin(latDistance/2) * Math.sin(latDistance/2) +
                    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                    Math.sin(longiDistance/2) * Math.sin(longiDistance/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c; //em Kms
    }
}
