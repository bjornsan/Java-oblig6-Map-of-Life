package mapoflife.model;

public class Bird extends Species {
    private boolean canFly, isPredator;
    private double wingspan;
    private double weight;

    public Bird() {}

    public Bird(String name, String scientificName, boolean canFly, boolean isPredator, double wingspan, double weight) {
        super(name, scientificName, "bird");
        this.canFly = canFly;
        this.isPredator = isPredator;
        this.wingspan = wingspan;
        this.weight = weight;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public boolean isPredator() {
        return isPredator;
    }

    public void setPredator(boolean predator) {
        isPredator = predator;
    }

    public double getWingspan() {
        return wingspan;
    }

    public void setWingspan(double wingspan) {
        this.wingspan = wingspan;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}

