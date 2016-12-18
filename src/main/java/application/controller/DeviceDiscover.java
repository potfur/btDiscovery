package application.controller;

import application.Controller;
import btDiscovery.Devices;

import java.util.Map;

public class DeviceDiscover implements Controller
{
    private Devices devices;

    public DeviceDiscover(Devices devices)
    {
        this.devices = devices;
    }

    public String handle(Map<String, String> args) throws Exception
    {
        devices.collect();

        return String.format("Found %d remote devices", devices.all().size());
    }
}
