package control;

import entity.*;
import enums.OccasionE;
import enums.WineTypeE;
import enums.SweetnessLevel;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WineLogic {
    private static WineLogic instance;

    public WineLogic() {
    }

    public static WineLogic getInstance() {
        if (instance == null) {
            instance = new WineLogic();
        }
        return instance;
    }

    public static List<Wine> getWineInfoByManufacturer(String manifactureNumber) {
        List<Wine> wineList = new ArrayList<>();
        String dbURL = Consts.CONN_STR;

        try (Connection conn = DriverManager.getConnection(dbURL)) {
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM TblWines  WHERE wineManufactureNumber = "
                    + manifactureNumber);

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(queryBuilder.toString())) {
                while (rs.next()) {
                        String catalogNumber = rs.getString("wineCatalogNumber");
                        String manufactureNumber = rs.getString("wineManufactureNumber");
                        String name = rs.getString("wineName");
                        String description = rs.getString("wineDescription");
                        int productionYear = rs.getInt("wineProductionYear");
                        float pricePerBottle = rs.getFloat("winePricePerBootle");
                        SweetnessLevel sweetnessLevel = SweetnessLevel.fromId(rs.getString("wineSweetnessLevel"));
                        WineTypeE wineType_ = WineTypeE.fromId(rs.getString("wineType"));


                    Wine wine = new Wine(catalogNumber,manufactureNumber, name, description, productionYear, pricePerBottle,
                            sweetnessLevel,null, wineType_);
                    wineList.add(wine);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wineList;
    }

    // Метод фильтрации через SQL или объекты
    public List<ChooseWineDTO> getFilteredWines(List<String> occasions, List<String> food, List<String> wineType) {

            return getFilteredWinesFromDB(occasions, food, wineType); // Фильтрация через SQL
    }

    // Метод фильтрации через SQL
    private List<ChooseWineDTO> getFilteredWinesFromDB(List<String> occasions, List<String> food, List<String> wineType) {
        List<ChooseWineDTO> wineList = new ArrayList<>();
        String dbURL = Consts.CONN_STR;

        try (Connection conn = DriverManager.getConnection(dbURL)) {
            StringBuilder queryBuilder = new StringBuilder("""
                        SELECT 
                            TblWines.wineCatalogNumber, 
                            TblWines.wineManufactureNumber, 
                            TblWines.wineName, 
                            TblWines.wineDescription, 
                            TblWines.wineProductionYear, 
                            TblWines.winePricePerBootle,  
                            TblWineTypes.wineTypeName AS wineType, 
                            TblWines.wineProductImage,
                            TbtOccasions.occasion,
                            TblFoods.foodName,
                            TblFoods.foodCode 
                        FROM TblWines  
                        LEFT JOIN TblWineTypes 
                            ON TblWineTypes.wineTypeSerialNumber = TblWines.wineType
                        LEFT JOIN TblWineTypeOccasion
                            ON TblWineTypes.wineTypeSerialNumber = TblWineTypeOccasion.wineTypeSerialNumber
                        LEFT JOIN TbtOccasions 
                            ON TblWineTypeOccasion.ID = TbtOccasions.ID
                        LEFT JOIN TblWineTypeFoodParings 
                            ON TblWineTypes.wineTypeSerialNumber = TblWineTypeFoodParings.wineTypeSerialNumber
                        LEFT JOIN TblFoods
                            ON TblWineTypeFoodParings.foodCode = TblFoods.foodCode
                        WHERE 1=1
                    """);

            // Добавляем фильтр по типу вина
            if (!wineType.isEmpty()) {
                queryBuilder.append(" AND TblWineTypes.wineTypeName IN  " +
                        "('" + wineType.stream().collect(Collectors.joining("', '")) + "')");
            }

            // Добавляем фильтр по ключевым словам
            if (!occasions.isEmpty()) {
                queryBuilder.append(" AND TbtOccasions.occasion IN  " +
                        "('" + occasions.stream().collect(Collectors.joining("', '")) + "')");
            }

            if (!food.isEmpty()) {
                queryBuilder.append(" AND TblFoods.foodName IN  " +
                        "('" + food.stream().collect(Collectors.joining("', '")) + "')");
            }

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(queryBuilder.toString())) {
                while (rs.next()) {
//                    String catalogNumber = rs.getString("wineCatalogNumber");
//                    String manufactureNumber = rs.getString("wineManufactureNumber");
                    String name = rs.getString("wineName");
                    String description = rs.getString("wineDescription");
//                    int productionYear = rs.getInt("wineProductionYear");
//                    float pricePerBottle = rs.getFloat("winePricePerBootle");
//                    String sweetnessLevelStr = rs.getString("level");
//                    SweetnessLevel sweetnessLevel = SweetnessLevel.valueOf(sweetnessLevelStr.toUpperCase());
//                    String wineTypeStr = rs.getString("wineType");
//                    WineTypeE wineType_ = WineTypeE.valueOf(wineTypeStr.toUpperCase());

                    String wineTypeStr = rs.getString("wineType");
                    WineTypeE wineTypeE = WineTypeE.valueOf(wineTypeStr.toUpperCase());

                    Food foodItem = new Food(rs.getString("foodName"), rs.getInt("foodCode"));
                    OccasionE occasionE = OccasionE.fromValue(rs.getString("occasion"));
                    ChooseWineDTO wine = new ChooseWineDTO(foodItem, occasionE, wineTypeE, description, name);
                    wineList.add(wine);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wineList;
    }


    // Метод для получения всех вин из базы
    public List<Wine> getWines() {
        List<Wine> wineList = new ArrayList<>();
        String dbURL = Consts.CONN_STR;

        try (Connection conn = DriverManager.getConnection(dbURL)) {
            String query = """
            SELECT 
                TblWines.wineCatalogNumber, 
                TblWines.wineManufactureNumber, 
                TblWines.wineName, 
                TblWines.wineDescription, 
                TblWines.wineProductionYear, 
                TblWines.winePricePerBootle, 
                TbtEnumSweetnessLevels.SweetnessLevel AS level, 
                TblWineTypes.wineTypeName AS wineType 
            FROM TblWines 
            LEFT JOIN TbtEnumSweetnessLevels 
                ON TbtEnumSweetnessLevels.ID = TblWines.wineSweetnessLevel 
            LEFT JOIN TblWineTypes 
                ON TblWineTypes.wineTypeSerialNumber = TblWines.wineType
        """;

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String catalogNumber = rs.getString("wineCatalogNumber");
                    String manufactureNumber = rs.getString("wineManufactureNumber");
                    String name = rs.getString("wineName");
                    String description = rs.getString("wineDescription");
                    int productionYear = rs.getInt("wineProductionYear");
                    float pricePerBottle = rs.getFloat("winePricePerBootle");
                    String sweetnessLevelStr = rs.getString("level");
                    SweetnessLevel sweetnessLevel = SweetnessLevel.valueOf(sweetnessLevelStr.toUpperCase());
                    String wineTypeStr = rs.getString("wineType");
                    WineTypeE wineType = WineTypeE.valueOf(wineTypeStr.toUpperCase());

                    Wine wine = new Wine(catalogNumber, manufactureNumber, name, description, productionYear,
                            pricePerBottle, sweetnessLevel, null, wineType);
                    wineList.add(wine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wineList;
    }
}
