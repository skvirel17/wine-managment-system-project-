package entity;

import enums.Location;
import enums.OccasionE;
import enums.Season;
import enums.WineTypeE;

import java.util.List;
import java.util.Map;

public class WineType {
    String serialNumber;
    WineTypeE name;
    Map<String, List<String>> foodParings;
    List <OccasionE> occasionERecommendations;
    Season season;
    Location location;

    public WineType(String serialNumber, WineTypeE name, List<OccasionE> occasionERecommendations, Map<String, List<String>> foodParings, Season season, Location location) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.occasionERecommendations = occasionERecommendations;
        this.foodParings = foodParings;
        this.season = season;
        this.location = location;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public WineTypeE getName() {
        return name;
    }

    public void setName(WineTypeE name) {
        this.name = name;
    }

    public Map<String, List<String>> getFoodParings() {
        return foodParings;
    }

    public void setFoodParings(Map<String, List<String>> foodParings) {
        this.foodParings = foodParings;
    }

    public List<OccasionE> getOccasionRecommendations() {
        return occasionERecommendations;
    }

    public void setOccasionRecommendations(List<OccasionE> occasionERecommendations) {
        this.occasionERecommendations = occasionERecommendations;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "WineType{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", foodParings=" + foodParings +
                ", occasionRecommendations=" + occasionERecommendations +
                ", season=" + season +
                ", location=" + location +
                '}';
    }
}



