package btDiscovery;

import java.util.Map;

public class Devices
{
    private final DeviceDiscoveryAgent agent;
    private final DeviceStorage storage;

    public Devices(DeviceDiscoveryAgent agent, DeviceStorage storage)
    {
        this.agent = agent;
        this.storage = storage;
    }

    public void collect() throws DeviceStorageException
    {
        try {
            for (Device device : agent.discover()) {
                storage.add(device);
            }
        } catch (Exception e) {
            throw new DeviceStorageException(e.getMessage());
        }
    }

    public Map<String, Device> all() throws DeviceStorageException
    {
        return storage.all();
    }

    public Device get(String address) throws DeviceStorageException
    {
        return storage.get(address);
    }
}
