package com.patrykstryczek.secondtry;

import com.patrykstryczek.secondtry.model.KnownNetwork;

/**
 * Created by Patrick on 2016-09-24.
 */

public class DistanceCalculator {
    private static double aValue = -36d;

    public double DistanceFromRSSI(Integer rssi){
       double distance = 0d;
       distance = Math.pow(10d, (rssi - aValue)/(-20d)) ;
    return distance;
    }

}