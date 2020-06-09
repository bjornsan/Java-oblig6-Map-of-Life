package mapoflife.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.jetbrains.annotations.NotNull;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bird.class, name = "bird"),
        @JsonSubTypes.Type(value = Invertebrate.class, name = "invertebrate"),
        @JsonSubTypes.Type(value = Amphibian.class, name = "amphibian")
})

public abstract class Species implements Comparable<Species>{
    private String name, scientificName, kindOfSpecies;

    public Species() {}

    public Species(String name, String scientificName, String kindOfSpecies) {
        this.name = name;
        this.scientificName = scientificName;
        this.kindOfSpecies = kindOfSpecies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getKindOfSpecies() {
        return kindOfSpecies;
    }

    public void setKindOfSpecies(String kindOfSpecies) {
        this.kindOfSpecies = kindOfSpecies;
    }

    @Override
    public int compareTo(@NotNull Species o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return name + " - " + scientificName;
    }
}
