package me.micrusa.autoscreensettings.utils;

import ca.rmen.sunrisesunset.SunriseSunset;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.micrusa.autoscreensettings.Constants;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
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
                showException(e);
            }
        }

        FileReader reader = null;
        Properties props = new Properties();
        try {
            reader = new FileReader(settingsFile);
            props.load(reader);
            reader.close();
        } catch (IOException e) {
            showException(e);
        }
        return props;
    }

    public static void saveProps(Properties props){
        File settingsFile = new File(Constants.SETTINGS_PATH);
        File appData = new File(Constants.APPDATA_PATH);

        if(!appData.exists())
            appData.mkdirs();

            try {
                if(!settingsFile.exists())
                    settingsFile.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(settingsFile);
                props.store(outputStream, null);
                outputStream.close();
            } catch (IOException e) {
                showException(e);
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

    public static void showException(Exception e){
        JOptionPane.showMessageDialog(new JFrame(), "There was an exception in the code: " + e.toString(), "AutoScreenSettings Exception",
                JOptionPane.ERROR_MESSAGE);
    }
}
