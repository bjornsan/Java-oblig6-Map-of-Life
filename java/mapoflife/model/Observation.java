package mapoflife.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class Observation implements Comparable<Observation> {
    private int id, numberOfSpecimen;
    private Species species;
    private Location location;
    private LocalDate dateOfObservation;
    private String name, pictureUrl, notes;


    public Observation() {}

    public Observation(int id, String name, int numberOfSpecimen, Species species, Location location, LocalDate dateOfObservation, String pictureUrl, String notes) {
        this.id = id;
        this.name = name;
        this.numberOfSpecimen = numberOfSpecimen;
        this.species = species;
        this.location = location;
        this.dateOfObservation = dateOfObservation;
        this.pictureUrl = pictureUrl;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String observationName) {
        this.name = observationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfSpecimen() {
        return numberOfSpecimen;
    }

    public void setNumberOfSpecimen(int numberOfSpecimen) {
        this.numberOfSpecimen = numberOfSpecimen;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDateOfObservation() {
        return dateOfObservation;
    }

    public void setDateOfObservation(LocalDate dateOfObservation) {
        this.dateOfObservation = dateOfObservation;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int compareTo(@NotNull Observation o) {
        return this.id - o.id;
    }

    @Override
    public String toString() {
        return String.format("Observationlog nr: %d - %s\nWe found %d %s(s) in %s on the planet %s", id, name, numberOfSpecimen, species.getName(), location.getName(), location.getPlanet());
    }
}
