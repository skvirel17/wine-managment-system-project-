package entity;

import enums.SweetnessLevel;
import enums.WineTypeE;


import java.io.Serializable;
import java.util.Objects;

public class Wine implements Serializable {
    private static final long serialVersionUID = 1L;
    public String catalogNumber;
    public String manufactureNumber;
    public String name;
    public String description;
    public int productionYear;
    public float pricePerBottle;
    public SweetnessLevel sweetnessLevel;
    public byte[] productImage;
    public WineTypeE wineType;

    public Wine(String catalogNumber, String manufactureNumber, String name, String description, int productionYear, float pricePerBottle,
                SweetnessLevel sweetnessLevel, byte[] productImage, WineTypeE wineType) {
        this.catalogNumber = catalogNumber;
        this.manufactureNumber = manufactureNumber;
        this.name = name;
        this.description = description;
        this.productionYear = productionYear;
        this.pricePerBottle = pricePerBottle;
        this.sweetnessLevel = sweetnessLevel;
        this.productImage = productImage;
        this.wineType = wineType;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getManufactureNumber() {
        return manufactureNumber;
    }

    public void setManufactureNumber(String manufactureNumber) {
        this.manufactureNumber = manufactureNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public float getPricePerBottle() {
        return pricePerBottle;
    }

    public void setPricePerBottle(float pricePerBottle) {
        this.pricePerBottle = pricePerBottle;
    }

    public SweetnessLevel getSweetnessLevel() {
        return sweetnessLevel;
    }

    public void setSweetnessLevel(SweetnessLevel sweetnessLevel) {
        this.sweetnessLevel = sweetnessLevel;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public WineTypeE getWineType() {
        return wineType;
    }

    public void setWineTypeId(WineTypeE wineTypeId) {
        this.wineType = wineTypeId;
    }

    @Override
    public String toString() {
        return "Wine{" +
                "catalogNumber='" + catalogNumber + '\'' +
                ", manufactureNumber=" + manufactureNumber +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productionYear=" + productionYear +
                ", pricePerBottle=" + pricePerBottle +
                ", sweetnessLevel=" + sweetnessLevel +
                ", productImage='" + productImage + '\'' +
                ", wineType=" + wineType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine = (Wine) o;
        return Objects.equals(catalogNumber, wine.catalogNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogNumber, manufactureNumber, name, description, productionYear, pricePerBottle, sweetnessLevel, productImage, wineType);
    }

}
