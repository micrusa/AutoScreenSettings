package me.micrusa.autoscreensettings.monitorController;

import com.sun.jna.platform.win32.*;

import java.util.ArrayList;

public class MonitorController {

    private ArrayList<Monitor> monitors = new ArrayList<Monitor>();

    public MonitorController(){
        System.out.println("Monitors: " + User32.INSTANCE.GetSystemMetrics(User32.SM_CMONITORS));

        User32.INSTANCE.EnumDisplayMonitors(null, null, new WinUser.MONITORENUMPROC() {

            public int apply(WinUser.HMONITOR hMonitor, WinDef.HDC hdc, WinDef.RECT rect, WinDef.LPARAM lparam)
            {
                System.out.println("Monitor handle: " + hMonitor);

                WinUser.MONITORINFOEX info = new WinUser.MONITORINFOEX();
                User32.INSTANCE.GetMonitorInfo(hMonitor, info);
                System.out.println(info.rcMonitor);

                WinDef.DWORDByReference pdwNumberOfPhysicalMonitors = new WinDef.DWORDByReference();
                Dxva2.INSTANCE.GetNumberOfPhysicalMonitorsFromHMONITOR(hMonitor, pdwNumberOfPhysicalMonitors);
                int monitorCount = pdwNumberOfPhysicalMonitors.getValue().intValue();

                System.out.println("Physical monitors for " + hMonitor + ": " + monitorCount);

                PhysicalMonitorEnumerationAPI.PHYSICAL_MONITOR[] physMons = new PhysicalMonitorEnumerationAPI.PHYSICAL_MONITOR[monitorCount];
                Dxva2.INSTANCE.GetPhysicalMonitorsFromHMONITOR(hMonitor, monitorCount, physMons);

                int i = 1;
                for (PhysicalMonitorEnumerationAPI.PHYSICAL_MONITOR mon : physMons)
                {
                    String desc = new String(mon.szPhysicalMonitorDescription);
                    monitors.add(new Monitor(mon.hPhysicalMonitor, desc));
                }

                return 1;
            }}, new WinDef.LPARAM(0));
    }

    public ArrayList<Monitor> getMonitors(){
        return this.monitors;
    }

}
