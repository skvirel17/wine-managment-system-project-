package entity;

import enums.Location;
import enums.Season;

import java.util.Objects;

public class Occasions {
    private  int Id;
    private  String nameOccasion;
    private  Location location;
    private  Season season;

    public Occasions(int Id, String nameOccasion, Location location, Season season) {
        this.Id = Id;
        this.nameOccasion = nameOccasion;
        this.location = location;
        this.season = season;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNameOccasion() {
        return nameOccasion;
    }

    public void setNameOccasion(String nameOccasion) {
        this.nameOccasion = nameOccasion;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Occasions occasions = (Occasions) o;
        return Id == occasions.Id && Objects.equals(nameOccasion, occasions.nameOccasion) && location == occasions.location && season == occasions.season;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nameOccasion, location, season);
    }

    @Override
    public String toString() {
        return "Occasions{" +
                "Id=" + Id +
                ", nameOccasion='" + nameOccasion + '\'' +
                ", location=" + location +
                ", season=" + season +
                '}';
    }
}
