package com.liondevs.fastfood.utils;


import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

@Slf4j
public class Utils {

    public static String getDistanceInKilometers(double lon1, double lat1, double lon2, double lat2, String unit){
        log.info("---lon1: {}",lon1);
        log.info("---lat1: {}",lat2);
        log.info("---lon2: {}",lon2);
        log.info("---lat1: {}",lat2);
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return "0";
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            DecimalFormat decimalFormat = new DecimalFormat("0.###");
            return decimalFormat.format(dist);
            //return  String.valueOf((dist));
        }
    }



}
