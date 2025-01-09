package entity;

import enums.SweetnessLevel;
import enums.WineType;

public class Wine {
    public static String catalogNumber;
    public static int manifactureNumber;
    public static String name;
    public static String description;
    public static int productionYear;
    public static float pricePerBottle;
    public static SweetnessLevel sweetnessLevel;
    public static String productImage;
    public static WineType wineType;

    public Wine(String catalogNumber, int manifactureNumber, String name, String description, int productionYear, float pricePerBottle,
                SweetnessLevel sweetnessLevel, String productImage,  WineType wineType) {
        this.catalogNumber = catalogNumber;
        this.manifactureNumber = manifactureNumber;
        this.name = name;
        this.description = description;
        this.productionYear = productionYear;
        this.pricePerBottle = pricePerBottle;
        this.sweetnessLevel = sweetnessLevel;
        this.productImage = productImage;
        this.wineType = wineType;
    }

    public static String getCatalogNumber() {
        return catalogNumber;
    }

    public static void setCatalogNumber(String catalogNumber) {
        Wine.catalogNumber = catalogNumber;
    }

    public static int getManifactureNumber() {
        return manifactureNumber;
    }

    public static void setManifactureNumber(int manifactureNumber) {
        Wine.manifactureNumber = manifactureNumber;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Wine.name = name;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Wine.description = description;
    }

    public static int getProductionYear() {
        return productionYear;
    }

    public static void setProductionYear(int productionYear) {
        Wine.productionYear = productionYear;
    }

    public static float getPricePerBottle() {
        return pricePerBottle;
    }

    public static void setPricePerBottle(float pricePerBottle) {
        Wine.pricePerBottle = pricePerBottle;
    }

    public static SweetnessLevel getSweetnessLevel() {
        return sweetnessLevel;
    }

    public static void setSweetnessLevel(SweetnessLevel sweetnessLevel) {
        Wine.sweetnessLevel = sweetnessLevel;
    }

    public static String getProductImage() {
        return productImage;
    }

    public static void setProductImage(String productImage) {
        Wine.productImage = productImage;
    }

    public static WineType getWineTypeId() {
        return wineType;
    }

    public static void setWineTypeId(WineType wineTypeId) {
        Wine.wineType = wineTypeId;
    }

    @Override
    public String toString() {
        return "Wine{}";
    }
}
