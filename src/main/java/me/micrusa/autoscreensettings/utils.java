package me.micrusa.autoscreensettings;

import ca.rmen.sunrisesunset.SunriseSunset;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Properties;

public class utils {

    public static Properties getProps(){
        File settingsFile = new File(Constants.SETTINGS_PATH);
        File appData = new File(Constants.APPDATA_PATH);

        if(!appData.exists())
            appData.mkdirs();

        if(!settingsFile.exists()) {
            try {
                settingsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileReader reader = null;
        try {
            reader = new FileReader(settingsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties props = new Properties();

        try {
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static void saveProps(Properties props){
        File settingsFile = new File(Constants.SETTINGS_PATH);
        File appData = new File(Constants.APPDATA_PATH);

        if(!appData.exists())
            appData.mkdirs();

        if(!settingsFile.exists()) {
            try {
                settingsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            props.store(new FileOutputStream(settingsFile), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static double[] getLocation() throws IOException {
        URL url = new URL("http://ip-api.com/json");
        URLConnection request = url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        return new double[]{rootobj.get("lat").getAsDouble(), rootobj.get("lon").getAsDouble()};
    }

    public static Calendar[] getSunriseSunset(double lat, double lon){
        Calendar TODAY = Calendar.getInstance();
        Calendar[] ss = SunriseSunset.getSunriseSunset(TODAY, lat, lon);
        System.out.println("Sunrise: " + ss[0].getTime() + ". Sunset: " + ss[1].getTime());
        return ss;
    }



}
