package unit.application.controller;

import application.controller.DeviceDetails;
import btDiscovery.DeviceStorageException;
import btDiscovery.Devices;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeviceDetailsTest
{
    @Test
    public void handleNonExistingDevice() throws Exception
    {
        Devices devices = mock(Devices.class);
        when(devices.get("12:34:56")).thenThrow(new DeviceStorageException("Forced"));

        String actual = (new DeviceDetails(devices)).handle(
            new HashMap<String, String>()
            {{
                put("details", "12:34:56");
            }}
        );
        assertEquals("Device 12:34:56 not found", actual);
    }

    @Test
    @Ignore
    public void handleExistingDevice() throws Exception
    {
    }
}
