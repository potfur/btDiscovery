package unit.btDiscovery;

import btDiscovery.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DevicesTest
{
    private DeviceDiscoveryAgent agent;
    private DeviceStorage storage;

    @Before
    public void before() throws Exception
    {
        agent = mock(DeviceDiscoveryAgent.class);
        storage = mock(DeviceStorage.class);
    }

    @Test
    public void collectDevices() throws Exception
    {
        Device device = mock(Device.class);
        when(agent.discover()).thenReturn(new ArrayList<Device>() {{ add(device); }});

        Devices devices = new Devices(agent, storage);
        devices.collect();

        verify(storage, times(1)).add(device);
    }

    @Test
    public void fetchAllDevices() throws Exception
    {
        Map<String, Device> storedDevices = new HashMap<>();
        storedDevices.put("12:34:56", new Device("12:34:56", "some device"));
        when(storage.all()).thenReturn(storedDevices);

        Devices devices = new Devices(agent, storage);

        assertEquals(1, devices.all().size());
    }


    @Test
    public void getExistingDeviceByAddress() throws Exception
    {
        Device storedDevice = new Device("12:34:56", "some device");
        when(storage.get("12:34:56")).thenReturn(storedDevice);

        Devices devices = new Devices(agent, storage);

        assertEquals(storedDevice, devices.get("12:34:56"));
    }

    @Test(expected = DeviceStorageException.class)
    public void getNonExistingDeviceByAddress() throws Exception
    {
        when(storage.get("12:34:56")).thenThrow(new DeviceStorageException("Forced"));

        (new Devices(agent, storage)).get("12:34:56");
    }
}
