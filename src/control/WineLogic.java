import entity.Consts;
import entity.Wine;
import enums.WineTypeE;
import enums.SweetnessLevel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WineLogic {
    private static WineLogic instance;

    private WineLogic() {}

    public static WineLogic getInstance() {
        if (instance == null) {
            instance = new WineLogic();
        }
        return instance;
    }

    // Метод фильтрации через SQL или объекты
    public List<Wine> getFilteredWines(WineTypeE wineType, List<String> keywords, boolean useDatabase) {
        if (useDatabase) {
            return getFilteredWinesFromDB(wineType, keywords); // Фильтрация через SQL
        } else {
            return filterWinesInMemory(wineType, keywords); // Фильтрация через объекты
        }
    }

    // Метод фильтрации через SQL
    private List<Wine> getFilteredWinesFromDB(WineTypeE wineType, List<String> keywords) {
        List<Wine> wineList = new ArrayList<>();
        String dbURL = Consts.CONN_STR;

        try (Connection conn = DriverManager.getConnection(dbURL)) {
            StringBuilder queryBuilder = new StringBuilder("""
                SELECT TblWines.wineCatalogNumber, TblWines.wineManufactureNumber, TblWines.wineName, 
                    TblWines.wineDescription, TblWines.wineProductionYear, TblWines.winePricePerBootle, 
                    sl.SweetnessLevel AS level, wt.wineTypeName AS wineType, TblWines.wineProductImage 
                FROM TblWines 
                LEFT JOIN TbtEnumSweetnessLevels sl ON sl.ID = TblWines.wineSweetnessLevel 
                LEFT JOIN TblWineTypes wt ON wt.wineTypeSerialNumber = TblWines.wineType 
                WHERE 1=1
            """);

            // Добавляем фильтр по типу вина
            if (wineType != null) {
                queryBuilder.append(" AND wt.wineTypeName = ? ");
            }

            // Добавляем фильтр по ключевым словам
            if (keywords != null && !keywords.isEmpty()) {
                queryBuilder.append(" AND (");
                for (int i = 0; i < keywords.size(); i++) {
                    queryBuilder.append(" TblWines.wineDescription LIKE ? ");
                    if (i < keywords.size() - 1) {
                        queryBuilder.append(" OR ");
                    }
                }
                queryBuilder.append(") ");
            }

            try (PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
                int paramIndex = 1;

                // Устанавливаем параметры для типа вина
                if (wineType != null) {
                    pstmt.setString(paramIndex++, wineType.name());
                }

                // Устанавливаем параметры для ключевых слов
                if (keywords != null && !keywords.isEmpty()) {
                    for (String keyword : keywords) {
                        pstmt.setString(paramIndex++, "%" + keyword + "%");
                    }
                }

                ResultSet rs = pstmt.executeQuery();
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
                    WineTypeE wineTypeFromDB = WineTypeE.valueOf(wineTypeStr.toUpperCase());
                    byte[] productImage = rs.getBytes("wineProductImage");

                    Wine wine = new Wine(catalogNumber, manufactureNumber, name, description, productionYear,
                            pricePerBottle, sweetnessLevel, productImage, wineTypeFromDB);
                    wineList.add(wine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wineList;
    }

    // Метод фильтрации через объекты
    private List<Wine> filterWinesInMemory(WineTypeE wineType, List<String> keywords) {
        List<Wine> allWines = getWines(); // Загружаем все вина
        List<Wine> filteredWines = new ArrayList<>();

        for (Wine wine : allWines) {
            boolean matches = true;

            // Фильтрация по типу вина
            if (wineType != null && wine.getWineType() != wineType) {
                matches = false;
            }

            // Фильтрация по ключевым словам
            if (matches && keywords != null && !keywords.isEmpty()) {
                boolean keywordMatches = keywords.stream().anyMatch(keyword ->
                        wine.getDescription().toLowerCase().contains(keyword.toLowerCase()));
                if (!keywordMatches) {
                    matches = false;
                }
            }

            if (matches) {
                filteredWines.add(wine);
            }
        }

        return filteredWines;
    }

    // Метод для получения всех вин из базы
    public List<Wine> getWines() {
        List<Wine> wineList = new ArrayList<>();
        String dbURL = Consts.CONN_STR;

        try (Connection conn = DriverManager.getConnection(dbURL)) {
            String query = """
                SELECT TblWines.wineCatalogNumber, TblWines.wineManufactureNumber, TblWines.wineName, 
                    TblWines.wineDescription, TblWines.wineProductionYear, TblWines.winePricePerBootle, 
                    sl.SweetnessLevel AS level, wt.wineTypeName AS wineType, TblWines.wineProductImage 
                FROM TblWines 
                LEFT JOIN TbtEnumSweetnessLevels sl ON sl.ID = TblWines.wineSweetnessLevel 
                LEFT JOIN TblWineTypes wt ON wt.wineTypeSerialNumber = TblWines.wineType 
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
                    byte[] productImage = rs.getBytes("wineProductImage");

                    Wine wine = new Wine(catalogNumber, manufactureNumber, name, description, productionYear,
                            pricePerBottle, sweetnessLevel, productImage, wineType);
                    wineList.add(wine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wineList;
    }
}
