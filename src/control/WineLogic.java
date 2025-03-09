package control;

import entity.*;
import enums.OccasionE;
import enums.WineTypeE;
import enums.SweetnessLevel;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static void removeWineById(String id) {
        String dbURL = Consts.CONN_STR;

        try (Connection conn = DriverManager.getConnection(dbURL)) {
            conn.setAutoCommit(false); // Начинаем транзакцию

            try (PreparedStatement pstmt1 = conn.prepareStatement(
                    "DELETE FROM TBWINELSTORAGELOCATIONS WHERE wineCatalogNumber = ?")) {
                pstmt1.setString(1, id);
                pstmt1.executeUpdate();
            }

            try (PreparedStatement pstmt2 = conn.prepareStatement(
                    "DELETE FROM TblWines WHERE wineCatalogNumber = ?")) {
                pstmt2.setString(1, id);
                int affectedRows = pstmt2.executeUpdate();

                conn.commit(); // Фиксируем транзакцию
            } catch (SQLException e) {
                conn.rollback(); // Откатываем изменения, если ошибка
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public boolean addWine(Wine wine) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_INS_MANUFACTURE)) {

                stmt.setString(1, wine.getManufactureNumber());
                stmt.setString(2, wine.getName());
                stmt.setString(3, wine.getDescription());
                stmt.setInt(4, wine.getProductionYear());
                stmt.setFloat(5, wine.getPricePerBottle());
                stmt.setString(5, String.valueOf(wine.getWineType()));
                stmt.setString(5, String.valueOf(wine.getSweetnessLevel()));
                stmt.setString(5, Arrays.toString(wine.getProductImage()));
                stmt.setString(5, wine.getCatalogNumber());

                int affectedRows = stmt.executeUpdate();
                return affectedRows > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Удаление производителя по ID
     */
    public static boolean removeWine(String catalogNumber) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
                conn.setAutoCommit(false); // Начинаем транзакцию

                try {
                    // Удаляем зависимости перед удалением вина
                    try (PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM TbWinelStorageLocations WHERE wineCatalogNumber = ?");
                         PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM TblCustomerDoRegularOrderWithOthers WHERE wineTypeSerialNumber = ?");
                         PreparedStatement stmt3 = conn.prepareStatement("DELETE FROM TblOrders WHERE orderNumber IN (SELECT orderNumber FROM TblCustomerDoRegularOrderWithOthers WHERE wineTypeSerialNumber = ?)");
                         PreparedStatement stmt4 = conn.prepareStatement("DELETE FROM TblUrgentOrders WHERE orderNumber IN (SELECT orderNumber FROM TblOrders WHERE orderEmployee IN (SELECT ID FROM TblEmployees WHERE ID IN (SELECT orderEmployee FROM TblOrders WHERE orderNumber IN (SELECT orderNumber FROM TblCustomerDoRegularOrderWithOthers WHERE wineTypeSerialNumber = ?))))");
                         PreparedStatement stmt5 = conn.prepareStatement("DELETE FROM TblWines WHERE wineCatalogNumber = ?")) {

                        stmt1.setString(1, catalogNumber);
                        stmt1.executeUpdate();

                        stmt2.setString(1, catalogNumber);
                        stmt2.executeUpdate();

                        stmt3.setString(1, catalogNumber);
                        stmt3.executeUpdate();

                        stmt4.setString(1, catalogNumber);
                        stmt4.executeUpdate();

                        stmt5.setString(1, catalogNumber);
                        int affectedRows = stmt5.executeUpdate();

                        conn.commit(); // Подтверждаем изменения
                        return affectedRows > 0;
                    }
                } catch (SQLException e) {
                    conn.rollback(); // Откат транзакции при ошибке
                    e.printStackTrace();
                } finally {
                    conn.setAutoCommit(true);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * Редактирование информации о производителе
     */
    public boolean editWine(Wine wine) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPD_MANUFACTURE)) {

                stmt.setString(1, wine.getManufactureNumber());
                stmt.setString(2, wine.getName());
                stmt.setString(3, wine.getDescription());
                stmt.setInt(4, wine.getProductionYear());
                stmt.setFloat(5, wine.getPricePerBottle());
                stmt.setString(5, String.valueOf(wine.getWineType()));
                stmt.setString(5, String.valueOf(wine.getSweetnessLevel()));
                stmt.setString(5, Arrays.toString(wine.getProductImage()));
                stmt.setString(5, wine.getCatalogNumber());

                int affectedRows = stmt.executeUpdate();
                return affectedRows > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
