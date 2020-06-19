package me.micrusa.autoscreensettings;

import me.micrusa.autoscreensettings.TrayIcon.DisplayTray;
import me.micrusa.autoscreensettings.utils.utils;

import java.util.Properties;

public class Main {


    public static void main(String[] args) {
        Properties props = utils.getProps();
        if(!props.getProperty(Constants.PROP_VER, "a").equals(Constants.PROPS_CURRENT_VER)){
            props.setProperty(Constants.PROP_VER, Constants.PROPS_CURRENT_VER);
            props.setProperty(Constants.PROP_BRIGHTNESS_ENABLED, "true");
            props.setProperty(Constants.PROP_NORMAL_BRIGHTNESS, "70");
            props.setProperty(Constants.PROP_NIGHT_BRIGHTNESS, "40");
            props.setProperty(Constants.PROP_CONTRAST_ENABLED, "false");
            props.setProperty(Constants.PROP_NORMAL_CONTRAST, "70");
            props.setProperty(Constants.PROP_NIGHT_CONTRAST, "85");
            utils.saveProps(props);
        }
        MainService.setupMainService().startService();
    }

}
