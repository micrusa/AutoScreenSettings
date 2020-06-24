package me.micrusa.autoscreensettings;

public class Constants {

    public static final String APPDATA_PATH = System.getenv("APPDATA") + "/AutoScreenSettings/";
    public static final String SETTINGS_PATH = APPDATA_PATH + "settings.properties";
    public static final String APP_PATH = APPDATA_PATH + "app.jar";
    public static final String START_SCRIPT = "java -jar " + APP_PATH + " autorun";
    public static final String UPDATE_SCRIPT = "java -jar " + APP_PATH + " update";

    public static final String PROPS_CURRENT_VER = "2";
    public static final String PROP_VER = "ver";
    public static final String PROP_CONTRAST_ENABLED = "enable_contrast";
    public static final String PROP_NORMAL_CONTRAST = "contrast";
    public static final String PROP_NIGHT_CONTRAST = "night_contrast";
    public static final String PROP_BRIGHTNESS_ENABLED = "enable_brightness";
    public static final String PROP_NORMAL_BRIGHTNESS = "brightness";
    public static final String PROP_NIGHT_BRIGHTNESS = "night_brightness";
    public static final String PROP_RGB_ENABLED = "enable_rgb";
    public static final String PROP_NORMAL_RGB = "rgb";
    public static final String PROP_NIGHT_RGB = "night_rgb";

}
