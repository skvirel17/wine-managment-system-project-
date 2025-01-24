package entity;

import control.WineLogic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ManufactureDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	public String manufactureNumber;



	public ManufactureDetails(String manifactureNumber){
		this.manufactureNumber = manifactureNumber;

	}


	public String getManifactureNumber() {
		return manufactureNumber;
	}

	public void setManifactureNumber(String manifactureNumber) {
		this.manufactureNumber = manifactureNumber;
	}




	public List<Wine> getWinesByManufactureNumber() {
		// Получаем список всех вин (вы можете реализовать собственный метод получения данных)
		List<Wine> allWines = WineLogic.getInstance().getWines();

		// Фильтруем вина, которые принадлежат данному производителю
		List<Wine> winesByManufacture = new ArrayList<>();
		for (Wine wine : allWines) {
			if (wine.getManufactureNumber().equals(this.manufactureNumber)) {
				winesByManufacture.add(wine);
			}
		}
		return winesByManufacture;
	}


}
