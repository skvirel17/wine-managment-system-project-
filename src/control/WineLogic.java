package control;
import entity.Consts;
import entity.Wine;
import enums.SweetnessLevel;
import enums.WineType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class WineLogic {

    public static List<Wine> getWineInfoByManufacturer(String manufactureNumber) {
        List<Wine> wineList = new ArrayList<>();

        // Путь к вашей базе данных Access
        String dbURL = Consts.CONN_STR; // Замените на правильный путь

        try {
            // Подключение к базе данных
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();

            // SQL-запрос для получения данных о винах производителя
            String query = """
                SELECT TblWines.wineCatalogNumber, TblWines.wineManufactureNumber, TblWines.wineName, 
                    TblWines.wineDescription, TblWines.wineProductionYear, TblWines.winePricePerBootle, 
                    sl.SweetnessLevel as level, wt.wineTypeName as wineType, TblWines.wineProductImage 
                FROM TblWines 
                LEFT JOIN TbtEnumSweetnessLevels sl ON sl.ID = TblWines.wineSweetnessLevel 
                LEFT JOIN TblWineTypes wt ON wt.wineTypeSerialNumber = TblWines.wineType 
                WHERE TblWines.wineManufactureNumber = """ + manufactureNumber;
            ResultSet rs = stmt.executeQuery(query);

            // Чтение данных из базы и создание объектов Wine
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

                // Создание объекта Wine и добавление в список
                Wine wine = new Wine(catalogNumber, manufacturyNumber, name, description, productionYear,
                        pricePerBottle, sweetnessLevel, productImage ,wineType);
                wineList.add(wine);
            }

            // Закрытие ресурсов
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wineList;
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
