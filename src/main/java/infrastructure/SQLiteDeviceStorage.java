package infrastructure;

import btDiscovery.Device;
import btDiscovery.DeviceStorage;
import btDiscovery.DeviceStorageException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SQLiteDeviceStorage implements DeviceStorage
{
    private Connection connection;

    public SQLiteDeviceStorage(Connection connection)
    {
        this.connection = connection;
        ensureDeviceTable();
    }

    private void ensureDeviceTable()
    {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(
                "CREATE TABLE device ( address CHAR(32) PRIMARY KEY NOT NULL, name TEXT);"
            );
        } catch (Exception e) {
        }
    }

    @Override
    public void add(Device device) throws DeviceStorageException
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO device (address, name) VALUES (?, ?)"
            );
            stmt.setString(1, device.address());
            stmt.setString(2, device.name());
            stmt.execute();
        } catch (SQLException e) {
            throw new DeviceStorageException("Unable to store device");
        }
    }

    @Override
    public Map<String, Device> all() throws DeviceStorageException
    {
        Map<String, Device> devices = new HashMap<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM device;");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                devices.put(
                    result.getString("address"),
                    new Device(
                        result.getString("address"),
                        result.getString("name")
                    )
                );
            }
        } catch (SQLException e) {
            throw new DeviceStorageException("Unable to read stored devices");
        }

        return devices;
    }

    @Override
    public int count() throws DeviceStorageException
    {
        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT count(*) FROM device;");
            return result.getInt(1);
        } catch (SQLException e) {
            throw new DeviceStorageException("Unable to read number of stored devices");
        }
    }

    @Override
    public Device get(String address) throws DeviceStorageException
    {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM device WHERE address = ? LIMIT 1"
            );
            stmt.setString(1, address);
            ResultSet result = stmt.executeQuery();

            if (!result.next()) {
                throw new DeviceStorageException(String.format("Device %s not found", address));
            }

            return new Device(
                result.getString("address"),
                result.getString("name")
            );
        } catch (SQLException e) {
            throw new DeviceStorageException("Unable to read device");
        }
    }
}
