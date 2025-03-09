package control;

import entity.StorageLocation;
import entity.WineStorageLocation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.*;

public class StorageLogic {
    private static StorageLogic _instance;

    private StorageLogic() { }

    public static StorageLogic getInstance() {
        if (_instance == null)
            _instance = new StorageLogic();
        return _instance;
    }




// שליפת יין המאוחסן בכל מיקום מסוים
public ArrayList<WineStorageLocation> getStorageLocations() {
    ArrayList<WineStorageLocation> results = new ArrayList<>();
    try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        String sql = "SELECT s.locationNumber, s.locationName, w.botleCount, w.wineCatalogNumber, w.wineManifactureNumber " +
                "FROM TblStorageLocations AS s " +
                "LEFT JOIN TbWinelStorageLocations AS w ON s.locationNumber = w.locationNumber";

        try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String locationNumber = rs.getString("locationNumber");
                String locationName = rs.getString("locationName");
                Integer bottleCount = rs.getObject("botleCount") != null ? rs.getInt("botleCount") : null;
                String catalogNumber = rs.getString("wineCatalogNumber");
                String manufactureNumber = rs.getString("wineManifactureNumber");

                WineStorageLocation storage = new WineStorageLocation(
                        locationNumber, locationName, catalogNumber, manufactureNumber, bottleCount
                );
                results.add(storage);
            }
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return results;
}

    public boolean addStorageLocation(StorageLocation location) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_INS_STORAGE_LOCATION)) {
                stmt.setString(1, location.getStorageNumber());
                stmt.setString(2, location.getStorageName());
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addWineStorage(WineStorageLocation wineStorage) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_INS_WINE_STORAGE)) {
                stmt.setString(1, wineStorage.getStorageNumber());
                stmt.setInt(2, wineStorage.getBottleCount());
                stmt.setString(3, wineStorage.getCatalogNumber());
                stmt.setString(4, wineStorage.getManufactureNumber());
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStorageLocation(StorageLocation location) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPD_STORAGE_LOCATION)) {
                stmt.setString(1, location.getStorageName());
                stmt.setString(2, location.getStorageNumber());
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateWineStorage(WineStorageLocation wineStorage) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPD_WINE_STORAGE)) {
                stmt.setInt(1, wineStorage.getBottleCount());
                stmt.setString(2, wineStorage.getStorageNumber());
                stmt.setString(3, wineStorage.getCatalogNumber());
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeStorageLocation(String locationNumber) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_DEL_STORAGE_LOCATION)) {
                stmt.setString(1, locationNumber);
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeWineStorage(String locationNumber, String catalogNumber) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_DEL_WINE_STORAGE)) {
                stmt.setString(1, locationNumber);
                stmt.setString(2, catalogNumber);
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addStorage(WineStorageLocation newStorage) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_INS_WINE_STORAGE)) {
                stmt.setString(1, newStorage.getStorageNumber());
                stmt.setInt(2, newStorage.getBottleCount());
                stmt.setString(3, newStorage.getCatalogNumber());
                stmt.setString(4, newStorage.getManufactureNumber());
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editStorage(WineStorageLocation newStorage) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPD_WINE_STORAGE)) {
                stmt.setInt(1, newStorage.getBottleCount());
                stmt.setString(2, newStorage.getStorageNumber());
                stmt.setString(3, newStorage.getCatalogNumber());
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void removeStorage(String storageNumber) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_DEL_WINE_STORAGE)) {
                stmt.setString(1, storageNumber);
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}