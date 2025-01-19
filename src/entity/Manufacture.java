package entity;

import java.io.Serializable;
import java.util.Objects;

public class Manufacture implements Serializable {
    private static final long serialVersionUID = 1L;
    public String manufactureNumber;
    public String fullName;
    public int phoneNumber;
    public String addressManifacture;
    public String email;

    public Manufacture(String manifactureNumber, String fullName, int phoneNumber, String addressManifacture, String email){
        this.manufactureNumber = manifactureNumber;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.addressManifacture = addressManifacture;
        this.email = email;
    }
    public Manufacture(String manufactureNumber){
        this.manufactureNumber = manufactureNumber;
    }

    public String getManifactureNumber() {
        return manufactureNumber;
    }

    public void setManifactureNumber(String manifactureNumber) {
        this.manufactureNumber = manifactureNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressManifacture() {
        return addressManifacture;
    }

    public void setAddressManifacture(String addressManifacture) {
        this.addressManifacture = addressManifacture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Manifacture{" +
                "manifactureNumber='" + manufactureNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", addressManifacture='" + addressManifacture + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacture that = (Manufacture) o;
        return Objects.equals(manufactureNumber, that.manufactureNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufactureNumber, fullName, phoneNumber, addressManifacture, email);
    }
}
