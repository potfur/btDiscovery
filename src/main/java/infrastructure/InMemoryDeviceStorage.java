package infrastructure;

import btDiscovery.Device;
import btDiscovery.DeviceStorage;
import btDiscovery.DeviceStorageException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDeviceStorage implements DeviceStorage
{
    private Map<String, Device> storage = new HashMap<>();

    @Override
    public void add(Device device)
    {
        storage.put(device.address(), device);
    }

    @Override
    public Map<String, Device> all()
    {
        return storage;
    }

    @Override
    public int count()
    {
        return storage.size();
    }

    @Override
    public Device get(String address) throws DeviceStorageException
    {
        if (storage.containsKey(address)) {
            return storage.get(address);
        }

        throw new DeviceStorageException(String.format("Device %s not found", address));
    }
}
