package integration.infrastructure;

import btDiscovery.Device;
import btDiscovery.DeviceStorage;
import infrastructure.SQLiteDeviceStorage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class SQLiteDeviceStorageTest
{
    private final static String DATABASE_NAME = "device_storage_test.db";
    private static Connection connection;

    @BeforeClass
    public static void beforeClass() throws Exception
    {
        connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
    }

    @After
    public void after() throws Exception
    {
        Statement stmt = connection.createStatement();
        stmt.execute("DELETE FROM device; VACUUM");
    }

    @AfterClass
    public static void afterClass() throws Exception
    {
        connection.close();
        connection = null;

        (new File(DATABASE_NAME)).delete();
    }

    @Test
    public void addDevice() throws Exception
    {
        Device device = new Device("12:34:56", "some device");

        DeviceStorage storage = new SQLiteDeviceStorage(connection);
        storage.add(device);

        assertEquals(1, storage.count());
    }


    @Test
    public void fetchAllDevices() throws Exception
    {
        Device device = new Device("12:34:56", "some device");

        DeviceStorage storage = new SQLiteDeviceStorage(connection);
        storage.add(device);

        assertEquals(
            device.address(),
            storage.all().values().iterator().next().address()
        );
    }
}
