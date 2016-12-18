package integration.infrastructure;

import btDiscovery.Device;
import btDiscovery.DeviceStorage;
import infrastructure.InMemoryDeviceStorage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InMemoryDeviceStorageTest
{
    @Test
    public void addDevice() throws Exception
    {
        Device device = new Device("12:34:56", "some device");

        DeviceStorage storage = new InMemoryDeviceStorage();
        storage.add(device);

        assertEquals(1, storage.count());
    }


    @Test
    public void fetchAllDevices() throws Exception
    {
        Device device = new Device("12:34:56", "some device");

        DeviceStorage storage = new InMemoryDeviceStorage();
        storage.add(device);

        assertEquals(device, storage.all().values().iterator().next());
    }
}
