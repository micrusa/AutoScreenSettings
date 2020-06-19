package me.micrusa.autoscreensettings;

import me.micrusa.autoscreensettings.TrayIcon.DisplayTray;
import me.micrusa.autoscreensettings.tasks.MainTask;

import java.util.Timer;

public class MainService {

    private static MainService mainService;
    private Timer mainTaskTimer;
    private DisplayTray displayTray;

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
        mainTaskTimer.scheduleAtFixedRate(new MainTask(), 0, 15 * 60 * 1000 /*Every 15m*/);
        displayTray = new DisplayTray();
    }

    public void stopService(){
        mainTaskTimer.cancel();
    }

}
