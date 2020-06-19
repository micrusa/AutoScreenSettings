package me.micrusa.autoscreensettings.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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
                location = queryLocation();
                System.out.println("Got location " + location[0] + ", " + location[1]);
                lastQuery = System.currentTimeMillis();
            } catch (IOException e) {
                lastQuery = 0; //Set to 0 to retry in next task run
                utils.showException(e);
            }
            if(location == null)
                location = new double[]{0.0, 0.0}; //If no location set it to 0.0 to avoid NullPointerException
        }
    }

    private static double[] queryLocation() throws IOException {
        URL url = new URL("http://ip-api.com/json");
        URLConnection request = url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        if(rootobj.get("status").getAsString().toLowerCase().contains("fail")){
            utils.showException(new Exception("Failed getting location"));
            return null;
        }
        return new double[]{rootobj.get("lat").getAsDouble(), rootobj.get("lon").getAsDouble()};
    }


}
