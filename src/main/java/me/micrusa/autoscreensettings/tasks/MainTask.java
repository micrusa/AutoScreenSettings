package me.micrusa.autoscreensettings.tasks;

import ca.rmen.sunrisesunset.SunriseSunset;
import me.micrusa.autoscreensettings.Constants;
import me.micrusa.autoscreensettings.monitorController.Monitor;
import me.micrusa.autoscreensettings.monitorController.MonitorController;
import me.micrusa.autoscreensettings.utils.LocationUtils;
import me.micrusa.autoscreensettings.utils.SetScreenValues;
import me.micrusa.autoscreensettings.utils.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.TimerTask;

public class MainTask extends TimerTask {

    public void run() {
        System.out.println("Running MainTask");
        SetScreenValues.setValuesByLocation(LocationUtils.getLat(), LocationUtils.getLon());
    }
}
