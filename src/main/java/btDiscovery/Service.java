package btDiscovery;

import javax.bluetooth.UUID;

public interface Service
{
    UUID uuid();

    String name();
}
