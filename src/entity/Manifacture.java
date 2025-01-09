package entity;

public class Manifacture {
    public static String manifactureNumber;
    public static String fullName;
    public static int phoneNumber;
    public static String addressManifacture;
    public static String email;

    public Manifacture(String manifactureNumber, String fullName, int phoneNumber, String addressManifacture,  String email){
        this.manifactureNumber = manifactureNumber;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.addressManifacture = addressManifacture;
        this.email = email;
    }

    public static String getManifactureNumber() {
        return manifactureNumber;
    }

    public static void setManifactureNumber(String manifactureNumber) {
        Manifacture.manifactureNumber = manifactureNumber;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        Manifacture.fullName = fullName;
    }

    public static int getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(int phoneNumber) {
        Manifacture.phoneNumber = phoneNumber;
    }

    public static String getAddressManifacture() {
        return addressManifacture;
    }

    public static void setAddressManifacture(String addressManifacture) {
        Manifacture.addressManifacture = addressManifacture;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Manifacture.email = email;
    }

    @Override
    public String toString() {
        return "Manifacture{}";
    }
}
