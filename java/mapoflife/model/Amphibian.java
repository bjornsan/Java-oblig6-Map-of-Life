package mapoflife.model;

public class Amphibian extends Species {
    private int numberOfLegs;
    private boolean isPoisonous;

    public Amphibian(){}

    public Amphibian(String name, String scientificName, int numberOfLegs, boolean isPoisonous) {
        super(name, scientificName, "amphibian");
        this.numberOfLegs = numberOfLegs;
        this.isPoisonous = isPoisonous;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    public boolean isPoisonous() {
        return isPoisonous;
    }

    public void setPoisonous(boolean poisonous) {
        isPoisonous = poisonous;
    }

}
