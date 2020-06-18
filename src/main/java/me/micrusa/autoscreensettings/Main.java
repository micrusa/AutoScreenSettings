package me.micrusa.autoscreensettings;

import java.util.Properties;

public class Main {


    public static void main(String[] args) {
        Properties props = utils.getProps();
        if(!props.getProperty(Constants.PROP_VER).equals(Constants.PROPS_CURRENT_VER)){
            props.setProperty(Constants.PROP_VER, Constants.PROPS_CURRENT_VER);
            props.setProperty(Constants.PROP_NORMAL_BRIGHTNESS, "70");
            props.setProperty(Constants.PROP_NIGHT_BRIGHTNESS, "50");
            props.setProperty(Constants.PROP_NORMAL_CONTRAST, "70");
            props.setProperty(Constants.PROP_NIGHT_CONTRAST, "70");
            utils.saveProps(props);
        }
        MainService.setupMainService();
        MainService.getMainService().startService();
    }

}
