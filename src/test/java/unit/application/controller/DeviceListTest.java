package unit.application.controller;

import application.controller.DeviceList;
import btDiscovery.Device;
import btDiscovery.Devices;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeviceListTest
{
    @Test
    public void handle() throws Exception
    {
        Device device = new Device("12:34:56", "Mocked device");
        Devices devices = mock(Devices.class);
        when(devices.all()).thenReturn(new HashMap<String, Device>() {{ put(device.address(), device); }});

        String actual = (new DeviceList(devices)).handle(new HashMap<>());

        String expected = String.format("Address\t\t\tName\n%s\t%s\n", device.address(), device.name());
        assertEquals(expected, actual);
    }
}
