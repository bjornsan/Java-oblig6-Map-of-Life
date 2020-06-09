package mapoflife.model;

import java.util.ArrayList;

public class Location {
    private String name, planet;
    private double longitude, latitude;
    private ArrayList<String> bioms = new ArrayList<>();

    public Location() {}

    public Location(String name, String planet, double longitude, double latitude) {
        this.name = name;
        this.planet = planet;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void addBiom(String biom) {
        bioms.add(biom);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public ArrayList<String> getBioms() {
        return new ArrayList<>(bioms);
    }

    @Override
    public String toString() {
        return String.format("%s on planet %s, latitude: %d, longitude: %d.", name, planet, latitude, longitude);
    }

}
