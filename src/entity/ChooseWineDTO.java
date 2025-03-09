package entity;

import enums.OccasionE;
import enums.SweetnessLevel;
import enums.WineTypeE;

import java.util.Objects;

public class ChooseWineDTO {
    private Food food;
    private OccasionE occasion;
    private WineTypeE wineType;
    private String description;
    private String wineName;


    public ChooseWineDTO(Food food, OccasionE occasion, WineTypeE wineType,
                         String description, String wineName) {
        this.food = food;
        this.occasion = occasion;
        this.wineType = wineType;
        this.description = description;
        this.wineName = wineName;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public OccasionE getOccasion() {
        return occasion;
    }

    public void setOccasion(OccasionE occasion) {
        this.occasion = occasion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WineTypeE getWineType() {
        return wineType;
    }

    public void setWineType(WineTypeE wineType) {
        this.wineType = wineType;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }


    @Override
    public String toString() {
        return "ChooseWineDTO{" +
                "food=" + food +
                ", occasion=" + occasion +
                ", wineType=" + wineType +
                ", description='" + description + '\'' +
                ", wineName='" + wineName + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChooseWineDTO that = (ChooseWineDTO) o;
        return wineType == that.wineType && Objects.equals(description, that.description) && Objects.equals(wineName, that.wineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wineType, description, wineName);
    }
}
