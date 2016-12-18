package application.controller;

import application.Controller;
import btDiscovery.Device;
import btDiscovery.DeviceStorageException;
import btDiscovery.Devices;

import java.util.Map;

public class DeviceDetails implements Controller
{
    private Devices devices;

    public DeviceDetails(Devices devices)
    {
        this.devices = devices;
    }

    public String handle(Map<String, String> args) throws Exception
    {
        String address = args.get("details");

        try {
            Device device = devices.get(address);
            return String.format("Address: %s\nName: %s", device.address(), device.name());
        } catch (DeviceStorageException e) {
            return String.format("Device %s not found", address);
        }
    }
}
