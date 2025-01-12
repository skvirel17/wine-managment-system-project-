package control;
import entity.Consts;
import entity.Manufacture;
import entity.Wine;
import enums.SweetnessLevel;
import enums.WineType;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;


public class WineLogic {

    public static List<Wine> getWineInfoByManufacturer(String manufactureNumber) {
        List<Wine> wineList = new ArrayList<>();


        String dbURL = Consts.CONN_STR;//"jdbc:ucanaccess://C://database.accdb";

        try {

            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();


            String query = """
                SELECT TblWines.wineCatalogNumber, TblWines.wineManufactureNumber, TblWines.wineName, 
                    TblWines.wineDescription, TblWines.wineProductionYear, TblWines.winePricePerBootle, 
                    sl.SweetnessLevel as level, wt.wineTypeName as wineType, TblWines.wineProductImage 
                FROM TblWines 
                LEFT JOIN TbtEnumSweetnessLevels sl ON sl.ID = TblWines.wineSweetnessLevel 
                LEFT JOIN TblWineTypes wt ON wt.wineTypeSerialNumber = TblWines.wineType 
                WHERE TblWines.wineManufactureNumber = """ + manufactureNumber;
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {
                String catalogNumber = rs.getString("wineCatalogNumber");
                String manufacturyNumber = rs.getString("wineManufactureNumber");
                String name = rs.getString("wineName");
                String description = rs.getString("wineDescription");
                int productionYear = rs.getInt("wineProductionYear");
                float pricePerBottle = rs.getFloat("winePricePerBootle");
                String sweetnessLevelStr = rs.getString("level");  // אנחנו מקבלים את הערך כ-String
                SweetnessLevel sweetnessLevel = SweetnessLevel.valueOf(sweetnessLevelStr.toUpperCase());  // המרה לערך enum
                String wineTypeStr = rs.getString("wineType");
                WineType wineType = WineType.valueOf(wineTypeStr.toUpperCase());
                byte[] productImage = null;//rs.getBytes("wineProductImage");


                Wine wine = new Wine(catalogNumber, manufacturyNumber, name, description, productionYear,
                        pricePerBottle, sweetnessLevel, productImage ,wineType);
                wineList.add(wine);
            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wineList;
    }

    public static List<Wine> getWines() {
        List<Wine> wineList = new ArrayList<>();


        String dbURL = Consts.CONN_STR;//"jdbc:ucanaccess://C://database.accdb";

        try {

            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();


            String query = """
                SELECT TblWines.wineCatalogNumber, TblWines.wineManufactureNumber, TblWines.wineName, 
                    TblWines.wineDescription, TblWines.wineProductionYear, TblWines.winePricePerBootle, 
                    sl.SweetnessLevel as level, wt.wineTypeName as wineType, TblWines.wineProductImage 
                FROM TblWines 
                LEFT JOIN TbtEnumSweetnessLevels sl ON sl.ID = TblWines.wineSweetnessLevel 
                LEFT JOIN TblWineTypes wt ON wt.wineTypeSerialNumber = TblWines.wineType 
                """;
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {
                String catalogNumber = rs.getString("wineCatalogNumber");
                String manufacturyNumber = rs.getString("wineManufactureNumber");
                String name = rs.getString("wineName");
                String description = rs.getString("wineDescription");
                int productionYear = rs.getInt("wineProductionYear");
                float pricePerBottle = rs.getFloat("winePricePerBootle");
                String sweetnessLevelStr = rs.getString("level");  // אנחנו מקבלים את הערך כ-String
                SweetnessLevel sweetnessLevel = SweetnessLevel.valueOf(sweetnessLevelStr.toUpperCase());  // המרה לערך enum
                String wineTypeStr = rs.getString("wineType");
                WineType wineType = WineType.valueOf(wineTypeStr.toUpperCase());
                byte[] productImage = null;//rs.getBytes("wineProductImage");


                Wine wine = new Wine(catalogNumber, manufacturyNumber, name, description, productionYear,
                        pricePerBottle, sweetnessLevel, productImage ,wineType);
                wineList.add(wine);
            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wineList;
    }

    public void addNewData(List<Wine> wines) {
        for (Wine wine : wines) {
            if (getWines().contains(wine)) {
                if(ManufactureLogic.getInstance().getManufactures().contains(new Manufacture(wine.getManufactureNumber()))) {
                    updateWine(wine);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "It is not possible to add the wine because the manufacturer data is missing." +
                                    "(" + wine.getCatalogNumber() + ")" +
                                    " Please update the manufacturer information.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                addWine(wine);
            }
        }
    }

    private void addWine(Wine wine) {
        String dbURL = Consts.CONN_STR;//"jdbc:ucanaccess://C://database.accdb";

        try {
            Connection conn = DriverManager.getConnection(dbURL);

            String query = """
            INSERT INTO TblWines
            VALUES (?, ?, ?, ?, ?,
            (SELECT wineTypeSerialNumber FROM TblWineTypes WHERE LOWER(wineTypeName) = LOWER(?)),
                (SELECT ID FROM TbtEnumSweetnessLevels WHERE LOWER(SweetnessLevel) = LOWER(?)),
                ?, ?)
        """;

            PreparedStatement pstmt = conn.prepareStatement(query);


            pstmt.setString(1, wine.getCatalogNumber());
            pstmt.setString(2, wine.getName());
            pstmt.setString(3, wine.getDescription());
            pstmt.setInt(4, wine.getProductionYear());
            pstmt.setFloat(5, wine.getPricePerBottle());
            pstmt.setString(6, wine.getWineTypeId().toString());
            pstmt.setString(7, wine.getSweetnessLevel().toString());
            pstmt.setString(8, wine.getManufactureNumber());
            pstmt.setBytes(9, wine.getProductImage());

            int rowsAffected = pstmt.executeUpdate();


            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateWine(Wine wine) {
        String dbURL = Consts.CONN_STR;//"jdbc:ucanaccess://C://database.accdb";
        boolean isUpdated = false;

        try {
            Connection conn = DriverManager.getConnection(dbURL);

            String query = """
                UPDATE TblWines
                    SET wineManufactureNumber = ?,
                        wineName = ?,
                        wineDescription = ?,
                        wineProductionYear = ?,
                        winePricePerBootle = ?,
                        wineSweetnessLevel = (SELECT ID FROM TbtEnumSweetnessLevels WHERE SweetnessLevel = ?),
                        wineType = (SELECT wineTypeSerialNumber FROM TblWineTypes WHERE wineTypeName = ?)
                WHERE wineCatalogNumber = ?
            """;

            PreparedStatement pstmt = conn.prepareStatement(query);


            pstmt.setString(1, wine.getManufactureNumber());
            pstmt.setString(2, wine.getName());
            pstmt.setString(3, wine.getDescription());
            pstmt.setInt(4, wine.getProductionYear());
            pstmt.setFloat(5, wine.getPricePerBottle());
            pstmt.setString(6, wine.getSweetnessLevel().toString());
            pstmt.setString(7, wine.getWineTypeId().toString());
            pstmt.setString(8, wine.getCatalogNumber());


            int rowsAffected = pstmt.executeUpdate();

            isUpdated = rowsAffected > 0;

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static WineLogic instance;
    public static WineLogic getInstance() {
        if (instance == null) {
            instance = new WineLogic();
        }
        return instance;
    }
    private List<Wine> wineDatabase;
    public List<Wine> getAllWines() {
       return wineDatabase;
  }
}
