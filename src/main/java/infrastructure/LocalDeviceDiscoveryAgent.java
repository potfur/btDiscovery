package infrastructure;

import btDiscovery.Device;
import btDiscovery.DeviceDiscoveryAgent;

import javax.bluetooth.*;
import java.util.ArrayList;
import java.util.List;

public class LocalDeviceDiscoveryAgent implements DeviceDiscoveryAgent
{
    private final Object event = new Object();

    @Override
    public List<Device> discover() throws BluetoothStateException, InterruptedException
    {
        List<Device> devices = new ArrayList<>();

        DiscoveryListener collector = new DiscoveryListener()
        {
            @Override
            public void deviceDiscovered(javax.bluetooth.RemoteDevice btDevice, DeviceClass cod)
            {
                devices.add(Device.fromRemote(btDevice));
            }

            @Override
            public void inquiryCompleted(int discType)
            {
                synchronized (event) {
                    event.notify();
                }
            }

            @Override
            public void serviceSearchCompleted(int transId, int respCode)
            {
            }

            @Override
            public void servicesDiscovered(int transId, ServiceRecord[] serviceRecords)
            {
            }
        };

        LocalDevice
            .getLocalDevice()
            .getDiscoveryAgent()
            .startInquiry(DiscoveryAgent.GIAC, collector);

        synchronized (event) {
            event.wait();
        }

        return devices;
    }
}
