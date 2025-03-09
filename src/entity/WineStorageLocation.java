package entity;

public class WineStorageLocation extends StorageLocation {
    private String catalogNumber;
    private String manufactureNumber;
    private Integer bottleCount;

    public WineStorageLocation(String storageNumber, String storageName, String catalogNumber, String manufactureNumber, Integer bottleCount) {
        super(storageNumber, storageName);
        this.catalogNumber = catalogNumber;
        this.manufactureNumber = manufactureNumber;
        this.bottleCount = bottleCount;
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

    public Integer getBottleCount() {
        return bottleCount;
    }

    public void setBottleCount(Integer bottleCount) {
        this.bottleCount = bottleCount;
    }
}
