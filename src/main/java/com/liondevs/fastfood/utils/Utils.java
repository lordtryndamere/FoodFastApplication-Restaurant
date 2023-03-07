package com.liondevs.fastfood.utils;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;



public class Utils {

    public static double getDistanceInKilometers(double lon1, double lat1, double lon2, double lat2){
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

}
