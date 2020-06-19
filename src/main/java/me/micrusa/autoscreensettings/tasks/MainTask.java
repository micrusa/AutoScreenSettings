package me.micrusa.autoscreensettings.tasks;

import ca.rmen.sunrisesunset.SunriseSunset;
import me.micrusa.autoscreensettings.Constants;
import me.micrusa.autoscreensettings.monitorController.Monitor;
import me.micrusa.autoscreensettings.monitorController.MonitorController;
import me.micrusa.autoscreensettings.utils.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.TimerTask;

public class MainTask extends TimerTask {

    private long latestRun = 0;
    private double[] location;

    public void run() {
        Properties props = utils.getProps();
        MonitorController monitorController = new MonitorController();
        //Get location just once every 12h
        if (latestRun == 0 || System.currentTimeMillis() - latestRun >= 12 * 60 * 1000){
            try {
                location = utils.getLocation();
                latestRun = System.currentTimeMillis();
            } catch (IOException e) {
                utils.showException(e);
                latestRun = 0;
                return;
            }
        }
        if(SunriseSunset.isDay(location[0], location[1])){
            for (Monitor monitor : monitorController.getMonitors()){
                System.out.println("Applying settings for monitor " + monitor.getName());
                if(Boolean.parseBoolean(props.getProperty(Constants.PROP_BRIGHTNESS_ENABLED)))
                    monitor.setBrightness(Integer.parseInt(props.getProperty(Constants.PROP_NORMAL_BRIGHTNESS)));
                if(Boolean.parseBoolean(props.getProperty(Constants.PROP_CONTRAST_ENABLED)))
                    monitor.setContrast(Integer.parseInt(props.getProperty(Constants.PROP_NORMAL_BRIGHTNESS)));
            }
        } else {
            for (Monitor monitor : monitorController.getMonitors()){
                System.out.println("Applying settings for monitor " + monitor.getName());
                if(Boolean.parseBoolean(props.getProperty(Constants.PROP_BRIGHTNESS_ENABLED)))
                    monitor.setBrightness(Integer.parseInt(props.getProperty(Constants.PROP_NIGHT_BRIGHTNESS)));
                if(Boolean.parseBoolean(props.getProperty(Constants.PROP_CONTRAST_ENABLED)))
                    monitor.setContrast(Integer.parseInt(props.getProperty(Constants.PROP_NIGHT_BRIGHTNESS)));
            }
        }
    }
}
