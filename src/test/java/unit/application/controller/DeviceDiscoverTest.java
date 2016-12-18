package unit.application.controller;

import application.controller.DeviceDiscover;
import btDiscovery.Device;
import btDiscovery.Devices;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeviceDiscoverTest
{
    @Test
    public void handle() throws Exception
    {
        Device device = new Device("12:34:56", "Mocked device");
        Devices devices = mock(Devices.class);
        when(devices.all()).thenReturn(new HashMap<String, Device>() {{ put(device.address(), device); }});

        String actual = (new DeviceDiscover(devices)).handle(new HashMap<>());

        assertEquals("Found 1 remote devices", actual);
    }
}
