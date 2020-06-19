package me.micrusa.autoscreensettings.TrayIcon;

import me.micrusa.autoscreensettings.utils.utils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class DisplayTray {

    static TrayIcon trayIcon;

    public DisplayTray(){
        showTrayIcon();
    }

    public static void showTrayIcon(){
        if(!SystemTray.isSupported()){
            utils.showException(new Exception("SystemTray not supported"));
            return;
        }

        trayIcon = new TrayIcon(createIcon("/me.micrusa.autoscreensettings.TrayIcon/icon.png", ""), "AutoScreenSettings\nby @micrusa", new TrayMenu());
        final SystemTray tray = SystemTray.getSystemTray();

        try{
            tray.add(trayIcon);
        } catch(AWTException e) {
            utils.showException(e);
        }
    }

    protected static Image createIcon(String path, String desc){
        URL ImageURL = DisplayTray.class.getResource(path);
        return new ImageIcon(ImageURL, desc).getImage();
    }


}
