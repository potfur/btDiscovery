package btDiscovery;

import java.util.List;

public interface DeviceDiscoveryAgent
{
    public List<Device> discover() throws Exception;
}
