package me.micrusa.autoscreensettings.utils;

import java.io.IOException;

public class LocationUtils {
    private static double[] location;
    private static long lastQuery = 0;

    public static double getLat(){
        if(location == null || System.currentTimeMillis() - lastQuery >= 24 * 60 * 1000)
            calcLocation();
        return location[0];
    }

    public static double getLon(){
        if(location == null || System.currentTimeMillis() - lastQuery >= 24 * 60 * 1000)
            calcLocation();
        return location[1];
    }

    public static void calcLocation(){
        if(lastQuery == 0 || System.currentTimeMillis() - lastQuery >= 24 * 60 * 1000){
            try {
                location = utils.getLocation();
                System.out.println("Got location " + location[0] + ", " + location[1]);
                lastQuery = System.currentTimeMillis();
            } catch (IOException e) {
                lastQuery = 0; //Set to 0 to retry in next task run
                if(location == null)
                    location = new double[]{0.0, 0.0}; //If no location set it to 0.0 to avoid NullPointerException
                utils.showException(e);
            }
        }
    }


}
