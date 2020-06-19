package me.micrusa.autoscreensettings.TrayIcon.items;

import me.micrusa.autoscreensettings.MainService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainService.getMainService().stopService();
        System.exit(0);
    }
}
