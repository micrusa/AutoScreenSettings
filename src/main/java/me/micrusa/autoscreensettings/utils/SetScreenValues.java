package me.micrusa.autoscreensettings.utils;

import ca.rmen.sunrisesunset.SunriseSunset;
import me.micrusa.autoscreensettings.Constants;
import me.micrusa.autoscreensettings.monitorController.Monitor;
import me.micrusa.autoscreensettings.monitorController.MonitorController;

import java.util.Properties;

public class SetScreenValues {

    public static void setValuesByLocation(double lat, double lon){
        if(lat == 0 && lon == 0) {
            System.out.println("No lat and lon data. Values wont be set");
            return; //Don't run if there's no data
        }
        Properties props = utils.getProps();
        MonitorController monitorController = new MonitorController();
        if(SunriseSunset.isDay(lat, lon)){
            for (Monitor monitor : monitorController.getMonitors()){
                System.out.println("Applying settings for monitor " + monitor.getName());
                String[] rgb = props.getProperty(Constants.PROP_NORMAL_RGB).split(", ");
                if(Boolean.parseBoolean(props.getProperty(Constants.PROP_BRIGHTNESS_ENABLED)))
                    monitor.setBrightness(Integer.parseInt(props.getProperty(Constants.PROP_NORMAL_BRIGHTNESS)));
                if(Boolean.parseBoolean(props.getProperty(Constants.PROP_CONTRAST_ENABLED)))
                    monitor.setContrast(Integer.parseInt(props.getProperty(Constants.PROP_NORMAL_BRIGHTNESS)));
                if(Boolean.parseBoolean(props.getProperty(Constants.PROP_RGB_ENABLED)))
                    monitor.setRGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
            }
        } else {
            for (Monitor monitor : monitorController.getMonitors()){
                System.out.println("Applying settings for monitor " + monitor.getName());
                String[] rgb = props.getProperty(Constants.PROP_NIGHT_RGB).split(", ");
                if(Boolean.parseBoolean(props.getProperty(Constants.PROP_BRIGHTNESS_ENABLED)))
                    monitor.setBrightness(Integer.parseInt(props.getProperty(Constants.PROP_NIGHT_BRIGHTNESS)));
                if(Boolean.parseBoolean(props.getProperty(Constants.PROP_CONTRAST_ENABLED)))
                    monitor.setContrast(Integer.parseInt(props.getProperty(Constants.PROP_NIGHT_BRIGHTNESS)));
                if(Boolean.parseBoolean(props.getProperty(Constants.PROP_RGB_ENABLED)))
                    monitor.setRGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
            }
        }
    }


}
