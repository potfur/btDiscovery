package unit.btDiscovery;

import btDiscovery.Device;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeviceTest
{
    @Test
    public void address() throws Exception
    {
        Device device = new Device("12:34:56", "some device");

        assertEquals("12:34:56", device.address());
    }

    @Test
    public void name() throws Exception
    {
        Device device = new Device("12:34:56", "some device");

        assertEquals("some device", device.name());
    }
}
