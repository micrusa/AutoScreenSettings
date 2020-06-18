package me.micrusa.autoscreensettings;

import me.micrusa.autoscreensettings.tasks.MainTask;

import java.util.Timer;

public class MainService {

    private static MainService mainService;
    private static Timer mainTaskTimer;

    public MainService(){
        mainTaskTimer = new Timer();
    }

    public static void startService(){
        mainService = new MainService();
        mainTaskTimer.scheduleAtFixedRate(new MainTask(), 0, 20 * 60 * 1000 /*Every 20m*/);
    }

    public static void stopService(){
        mainTaskTimer.cancel();
    }

}
