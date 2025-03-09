package entity;

public class StorageLocation {
    private String storageNumber;
    private String storageName;


    public StorageLocation(String storageNumber, String storageName) {
        this.storageNumber = storageNumber;
        this.storageName = storageName;

    }

    public String getStorageNumber() {
        return storageNumber;
    }

    public void setStorageNumber(String storageNumber) {
        this.storageNumber = storageNumber;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

}
