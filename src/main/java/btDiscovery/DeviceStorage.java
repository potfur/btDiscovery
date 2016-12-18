package btDiscovery;

import java.util.Map;

public interface DeviceStorage
{
    void add(Device device) throws DeviceStorageException;

    Map<String, Device> all() throws DeviceStorageException;

    int count() throws DeviceStorageException;

    Device get(String id) throws DeviceStorageException;
}
