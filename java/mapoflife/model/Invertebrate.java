package mapoflife.model;

public class Invertebrate extends Species {
    private boolean canFly, isPoisonous, canBreathUnderWater;
    private int numberOfLegs, numberOfEyes;


    public Invertebrate() {}

    public Invertebrate(String name, String scientificName, boolean canFly, boolean isPoisonous, boolean canBreathUnderWater, int numberOfLegs, int numberOfEyes) {
        super(name, scientificName, "invertebrate");
        this.canFly = canFly;
        this.isPoisonous = isPoisonous;
        this.canBreathUnderWater = canBreathUnderWater;
        this.numberOfLegs = numberOfLegs;
        this.numberOfEyes = numberOfEyes;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public boolean isPoisonous() {
        return isPoisonous;
    }

    public void setPoisonous(boolean poisonous) {
        isPoisonous = poisonous;
    }

    public boolean isCanBreathUnderWater() {
        return canBreathUnderWater;
    }

    public void setCanBreathUnderWater(boolean canBreathUnderWater) {
        this.canBreathUnderWater = canBreathUnderWater;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    public int getNumberOfEyes() {
        return numberOfEyes;
    }

    public void setNumberOfEyes(int numberOfEyes) {
        this.numberOfEyes = numberOfEyes;
    }

}
