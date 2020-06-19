package me.micrusa.autoscreensettings.TrayIcon.items;

import com.sun.scenario.effect.impl.sw.java.JSWPerspectiveTransformPeer;
import me.micrusa.autoscreensettings.Constants;
import me.micrusa.autoscreensettings.utils.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class Settings implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel settingsPanel;

        Properties props = utils.getProps();

        JCheckBox enableBrightness = new JCheckBox("Enable brightness control",
                Boolean.parseBoolean(props.getProperty(Constants.PROP_BRIGHTNESS_ENABLED)));
        JLabel brightnessLabel = new JLabel("Normal Brightness: ", JLabel.RIGHT);
        JTextField brightness = new JTextField(props.getProperty(Constants.PROP_NORMAL_BRIGHTNESS));
        JLabel nightBrightnessLabel = new JLabel("Night Brightness", JLabel.RIGHT);
        JTextField nightBrightness = new JTextField(props.getProperty(Constants.PROP_NIGHT_BRIGHTNESS));
        JCheckBox enableContrast = new JCheckBox("Enable contrast control",
                Boolean.parseBoolean(props.getProperty(Constants.PROP_CONTRAST_ENABLED)));
        JLabel contrastLabel = new JLabel("Normal Contrast: ", JLabel.RIGHT);
        JTextField contrast = new JTextField(props.getProperty(Constants.PROP_NORMAL_CONTRAST));
        JLabel nightContrastLabel = new JLabel("Night contrast: ", JLabel.RIGHT);
        JTextField nightContrast = new JTextField(props.getProperty(Constants.PROP_NIGHT_CONTRAST));
        settingsPanel = new JPanel(false);
        settingsPanel.setLayout(new BoxLayout(settingsPanel,
                BoxLayout.X_AXIS));
        JPanel namePanel = new JPanel(false);
        namePanel.setLayout(new GridLayout(0, 1));
        namePanel.add(Box.createHorizontalStrut(10));
        namePanel.add(brightnessLabel);
        namePanel.add(nightBrightnessLabel);
        namePanel.add(Box.createHorizontalStrut(10));
        namePanel.add(contrastLabel);
        namePanel.add(nightContrastLabel);
        JPanel fieldPanel = new JPanel(false);
        fieldPanel.setLayout(new GridLayout(0, 1));
        fieldPanel.add(enableBrightness);
        fieldPanel.add(brightness);
        fieldPanel.add(nightBrightness);
        fieldPanel.add(enableContrast);
        fieldPanel.add(contrast);
        fieldPanel.add(nightContrast);
        settingsPanel.add(namePanel);
        settingsPanel.add(fieldPanel);

        // Connect or quit
        if (JOptionPane.showOptionDialog(null, settingsPanel,
                "Settings",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, new String[]{"Ok", "Cancel"},
                "Ok") != 0)
        props.setProperty(Constants.PROP_BRIGHTNESS_ENABLED, String.valueOf(enableBrightness.isSelected()));
        props.setProperty(Constants.PROP_NORMAL_BRIGHTNESS, brightness.getText());
        props.setProperty(Constants.PROP_NIGHT_BRIGHTNESS, nightBrightness.getText());
        props.setProperty(Constants.PROP_CONTRAST_ENABLED, String.valueOf(enableContrast.isSelected()));
        props.setProperty(Constants.PROP_NORMAL_CONTRAST, contrast.getText());
        props.setProperty(Constants.PROP_NIGHT_CONTRAST, nightContrast.getText());
        utils.saveProps(props);
    }
}
