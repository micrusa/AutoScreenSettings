package me.micrusa.autoscreensettings.monitorController;

import com.sun.jna.platform.win32.*;

public class Monitor {

    private WinNT.HANDLE handle;
    private String name;
    private int id;

    public Monitor(WinNT.HANDLE hPhysicalMonitor, String szPhysicalMonitorDescription, int Id) {
        this.handle = hPhysicalMonitor;
        this.name = szPhysicalMonitorDescription;
        this.id = Id;
    }

    public int getMonitorId(){
        return this.id;
    }

    public int[] getBrightness() throws Win32Exception {
        WinDef.DWORDByReference minBrightness = new WinDef.DWORDByReference();
        WinDef.DWORDByReference curBrightness = new WinDef.DWORDByReference();
        WinDef.DWORDByReference maxBrightness = new WinDef.DWORDByReference();

        Dxva2.INSTANCE.GetMonitorBrightness(this.handle, minBrightness, curBrightness, maxBrightness);

        return new int[]{minBrightness.getValue().intValue(), curBrightness.getValue().intValue(), maxBrightness.getValue().intValue()};
    }

    public void setBrightness(int i) throws Win32Exception {
        Dxva2.INSTANCE.SetMonitorBrightness(this.handle, i);
    }

    public int[] getContrast() throws Win32Exception {
        WinDef.DWORDByReference minContrast = new WinDef.DWORDByReference();
        WinDef.DWORDByReference curContrast = new WinDef.DWORDByReference();
        WinDef.DWORDByReference maxContrast = new WinDef.DWORDByReference();

        Dxva2.INSTANCE.GetMonitorContrast(this.handle, minContrast, curContrast, maxContrast);

        return new int[]{minContrast.getValue().intValue(), curContrast.getValue().intValue(), maxContrast.getValue().intValue()};
    }

    public void setContrast(int i) throws Win32Exception {
        Dxva2.INSTANCE.SetMonitorContrast(this.handle, i);
    }

    public String getTemperature() throws Win32Exception {
        HighLevelMonitorConfigurationAPI.MC_COLOR_TEMPERATURE.ByReference temp = new HighLevelMonitorConfigurationAPI.MC_COLOR_TEMPERATURE.ByReference();
        Dxva2.INSTANCE.GetMonitorColorTemperature(this.handle, temp);
        if (HighLevelMonitorConfigurationAPI.MC_COLOR_TEMPERATURE.MC_COLOR_TEMPERATURE_4000K.equals(temp))
            return "4000K";
        else if (HighLevelMonitorConfigurationAPI.MC_COLOR_TEMPERATURE.MC_COLOR_TEMPERATURE_5000K.equals(temp))
            return "5000K";
        else if (HighLevelMonitorConfigurationAPI.MC_COLOR_TEMPERATURE.MC_COLOR_TEMPERATURE_6500K.equals(temp))
            return "6500K";
        else if (HighLevelMonitorConfigurationAPI.MC_COLOR_TEMPERATURE.MC_COLOR_TEMPERATURE_7500K.equals(temp))
            return "7500K";
        else if (HighLevelMonitorConfigurationAPI.MC_COLOR_TEMPERATURE.MC_COLOR_TEMPERATURE_8200K.equals(temp))
            return "8200K";
        else if (HighLevelMonitorConfigurationAPI.MC_COLOR_TEMPERATURE.MC_COLOR_TEMPERATURE_9300K.equals(temp))
            return "9300K";
        else if (HighLevelMonitorConfigurationAPI.MC_COLOR_TEMPERATURE.MC_COLOR_TEMPERATURE_10000K.equals(temp))
            return "10000K";
        else if (HighLevelMonitorConfigurationAPI.MC_COLOR_TEMPERATURE.MC_COLOR_TEMPERATURE_11500K.equals(temp))
            return "11500K";
        else return "0K";
    }


}
