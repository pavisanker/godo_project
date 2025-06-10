package com.GoDo.godo.utilities_pack;

public class FareCalculator {

    public static long calculateAmount(long distanceInMeters, int capacity) {
        double distanceInKm = distanceInMeters / 1000.0;
        double amount;

        switch (capacity) {
            case 2:
                amount = 10 + distanceInKm * 1 ;
                break;
            case 5:
                amount = 30 + distanceInKm * 3 ;
                break;
            case 7:
                amount = 20 + distanceInKm * 5 ;
                break;
            default:
                amount = 50 + distanceInKm * 5 ; // default rule
        }

        return Math.round(amount*100);
    }
}

