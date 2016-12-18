package btDiscovery;

public class Device
{
    private String address;
    private String name;

    public Device(String address, String name)
    {
        this.address = address;
        this.name = name;
    }

    public static Device fromRemote(javax.bluetooth.RemoteDevice btDevice)
    {
        String address = btDevice.getBluetoothAddress();
        String name;
        try {
            name = btDevice.getFriendlyName(false);
        } catch (Exception e) {
            name = "";
        }

        return new Device(
            address,
            name
        );
    }

    public String address()
    {
        return address;
    }

    public String name()
    {
        return name;
    }
}
