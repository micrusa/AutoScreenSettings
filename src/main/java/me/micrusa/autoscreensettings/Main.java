package me.micrusa.autoscreensettings;

import me.micrusa.autoscreensettings.TrayIcon.DisplayTray;
import me.micrusa.autoscreensettings.utils.AppInstaller;
import me.micrusa.autoscreensettings.utils.utils;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Properties;

public class Main {

    private static File appFile;
    private static boolean DEBUG = false;


    public static void main(String[] args) {
        try {
            appFile = new File(Main.class.getProtectionDomain().
                    getCodeSource().
                    getLocation().toURI().toString());
        } catch (URISyntaxException e) {
            utils.showException(e);
        }
        if(!(args.length > 0 || appFile.getPath().toLowerCase().contains("appdata")) && !DEBUG)
            AppInstaller.openDialog();
        Properties props = utils.getProps();
        if(!props.getProperty(Constants.PROP_VER, "a").equals(Constants.PROPS_CURRENT_VER)){
            props.setProperty(Constants.PROP_VER, Constants.PROPS_CURRENT_VER);
            props.setProperty(Constants.PROP_BRIGHTNESS_ENABLED, "true");
            props.setProperty(Constants.PROP_NORMAL_BRIGHTNESS, "70");
            props.setProperty(Constants.PROP_NIGHT_BRIGHTNESS, "40");
            props.setProperty(Constants.PROP_CONTRAST_ENABLED, "false");
            props.setProperty(Constants.PROP_NORMAL_CONTRAST, "70");
            props.setProperty(Constants.PROP_NIGHT_CONTRAST, "85");
            props.setProperty(Constants.PROP_RGB_ENABLED, "true");
            props.setProperty(Constants.PROP_NORMAL_RGB, "50, 50, 50");
            props.setProperty(Constants.PROP_NIGHT_RGB, "50, 46, 39");
            utils.saveProps(props);
        }
        MainService.setupMainService().startService();
        if(args.length > 0 && args[0].toLowerCase().contains("update"))
            MainService.getMainService().getDisplayTray().showMsg("AutoScreenSettings", "App installed/updated successfully");
    }

}
