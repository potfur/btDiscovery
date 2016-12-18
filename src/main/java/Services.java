import application.Controller;
import application.controller.DeviceDetails;
import application.controller.DeviceDiscover;
import application.controller.DeviceList;
import btDiscovery.DeviceDiscoveryAgent;
import btDiscovery.DeviceStorage;
import btDiscovery.Devices;
import infrastructure.LocalDeviceDiscoveryAgent;
import infrastructure.SQLiteDeviceStorage;
import org.codejargon.feather.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;

public class Services
{
    @Provides
    @Singleton
    private DeviceDiscoveryAgent deviceDiscoveryAgent() throws Exception
    {
        return new LocalDeviceDiscoveryAgent();
    }

    @Provides
    @Singleton
    private DeviceStorage storage() throws Exception
    {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:btDiscovery.db");
        return new SQLiteDeviceStorage(connection);
    }

    @Provides
    private Devices devices() throws Exception
    {
        return new Devices(this.deviceDiscoveryAgent(), this.storage());
    }

    @Provides
    @Named("device-list")
    private Controller deviceList() throws Exception
    {
        return new DeviceList(this.devices());
    }

    @Provides
    @Named("device-discover")
    private Controller deviceDiscover() throws Exception
    {
        return new DeviceDiscover(this.devices());
    }

    @Provides
    @Named("device-details")
    private Controller deviceDetails() throws Exception
    {
        return new DeviceDetails(this.devices());
    }
}
