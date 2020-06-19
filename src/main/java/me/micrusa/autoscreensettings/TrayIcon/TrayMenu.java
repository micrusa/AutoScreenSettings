package me.micrusa.autoscreensettings.TrayIcon;

import me.micrusa.autoscreensettings.TrayIcon.items.Exit;
import me.micrusa.autoscreensettings.TrayIcon.items.Settings;

import java.awt.*;

public class TrayMenu extends PopupMenu {

    public TrayMenu(){
        MenuItem Settings = new MenuItem("Settings");
        MenuItem Exit = new MenuItem("Exit");

        this.add(Settings);
        this.addSeparator();
        this.add(Exit);

        Settings.addActionListener(new Settings());
        Exit.addActionListener(new Exit());

    }

}
