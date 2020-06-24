package me.micrusa.autoscreensettings.utils;

import com.google.common.io.Files;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;
import me.micrusa.autoscreensettings.Constants;
import me.micrusa.autoscreensettings.Main;
import me.micrusa.autoscreensettings.MainService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class AppInstaller {

    public static void openDialog(){
        int install;
        if(!new File(Constants.APP_PATH).exists()){
            install = JOptionPane.showConfirmDialog((Component) null, "Do you want to install AutoScreenSettings?",
                    "AutoScreenSettings installation", JOptionPane.OK_CANCEL_OPTION);
        } else {
            install = JOptionPane.showConfirmDialog((Component) null, "Do you want to update AutoScreenSettings?",
                    "AutoScreenSettings update", JOptionPane.OK_CANCEL_OPTION);
        }
        if(install != 0){
            System.exit(0);
        }
        File appFile;
        try {
            appFile = new File(Main.class.getProtectionDomain().
                    getCodeSource().
                    getLocation()
                    .toURI());
            Files.copy(appFile, new File(Constants.APP_PATH));
            Advapi32Util.registrySetStringValue(WinReg.HKEY_LOCAL_MACHINE,
                    "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run", "AutoScreenSettings", Constants.START_SCRIPT);
            System.out.println(Constants.START_SCRIPT);
            Runtime.getRuntime().exec(Constants.START_SCRIPT);
            System.exit(1);
        } catch (URISyntaxException | IOException e) {
            utils.showException("Error in installation", e);
        }
    }

}
