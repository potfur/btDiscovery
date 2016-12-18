package application.controller;

import application.Controller;
import btDiscovery.Device;
import btDiscovery.Devices;

import java.util.Map;

public class DeviceList implements Controller
{
    private Devices devices;

    public DeviceList(Devices devices)
    {
        this.devices = devices;
    }

    public String handle(Map<String, String> args) throws Exception
    {
        Map<String, Device> devices = this.devices.all();

        String output = "Address\t\t\tName\n";
        for (Device device : devices.values()) {
            output += String.format("%s\t%s\n", device.address(), device.name());
        }

        return output;
    }
}
