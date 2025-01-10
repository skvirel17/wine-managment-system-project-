package entity;

import java.math.BigDecimal;
import java.util.Objects;


public class ManufactureDetails {

	public String manifactureNumber;
	public String fullName;
	public int phoneNumber;
	public String addressManifacture;
	public String email;


	public static final int MAX_PRODUCT_ID = 10;
    public static final int MAX_QUANTITY = 10;
    public static final int MAX_DISCOUNT = 3;


	public ManufactureDetails(String manifactureNumber, String fullName, int phoneNumber, String addressManifacture, String email){
		this.manifactureNumber = manifactureNumber;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.addressManifacture = addressManifacture;
		this.email = email;
	}


	public String getManifactureNumber() {
		return manifactureNumber;
	}

	public void setManifactureNumber(String manifactureNumber) {
		this.manifactureNumber = manifactureNumber;
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
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		ManufactureDetails that = (ManufactureDetails) o;
		return Objects.equals(manifactureNumber, that.manifactureNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(manifactureNumber);
	}

	@Override
	public String toString() {
		return "ManufactureDetails{" +
				"manifactureNumber='" + manifactureNumber + '\'' +
				", fullName='" + fullName + '\'' +
				", phoneNumber=" + phoneNumber +
				", addressManifacture='" + addressManifacture + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
