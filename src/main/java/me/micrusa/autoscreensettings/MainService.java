package me.micrusa.autoscreensettings;

import me.micrusa.autoscreensettings.tasks.MainTask;

import java.util.Timer;

public class MainService {

    private static MainService mainService;
    private Timer mainTaskTimer;

    public MainService(){
        mainTaskTimer = new Timer();
    }

    public static MainService getMainService(){
        return mainService;
    }

    public static MainService setupMainService(){
        mainService = new MainService();
        return mainService;
    }

    public void startService(){
        mainTaskTimer.scheduleAtFixedRate(new MainTask(), 0, 20 * 60 * 1000 /*Every 20m*/);
    }

    public void stopService(){
        mainTaskTimer.cancel();
    }

}
