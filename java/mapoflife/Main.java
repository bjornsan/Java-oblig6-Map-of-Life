package mapoflife;

import mapoflife.model.*;
import mapoflife.repository.ObservationRepository;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
       ObservationRepository repository = generateObservations();
       printObservations(repository);
       repository.writeToJSON();
    }

    private static ObservationRepository generateObservations() {
        ObservationRepository repository = new ObservationRepository("observations.json");

        Observation nr1 = new Observation(1, "Spotted Eagles",10,
                new Bird("Harpy Eagle","Harpia harpyja", true, true,176.0, 7),
                new Location("Brazil", "Earth", -46.625290, -23.533773),
                LocalDate.now(), "https://upload.wikimedia.org/wikipedia/commons/d/dc/Harpy_Eagle%2C_South_America.jpeg", "beautiful bird");
        nr1.getLocation().addBiom("Tropical lowland rainforests");

        Observation nr2 = new Observation(2, "Giantic beast spotted",1,
                new Bird("Pterodactyl","Pterodactyl", true, true,1.04, 150),
                new Location("Hawaii", "Earth", -155.844437, 19.741755),
                LocalDate.now(), "https://upload.wikimedia.org/wikipedia/commons/a/a0/Pterodactylus_BMMS7_life.png", "scary, almost caught me!");
        nr2.getLocation().addBiom("Volcanos");
        nr2.getLocation().addBiom("Open ocean");

        Observation nr3 = new Observation(3, "Invasion",60,
                new Amphibian("Golden Frog","Atelopus zeteki", 4, true),
                new Location("Panama", "Earth", -79.516670, 8.983333),
                LocalDate.now(), "https://upload.wikimedia.org/wikipedia/commons/f/f6/Panamanian_golden_frog_005.jpg", "Don't underestimate its poison.");
        nr3.getLocation().addBiom("Wet rainforests");
        nr3.getLocation().addBiom("Dry cloud forests");

        Observation nr4 = new Observation(4, "Strange salamander",3,
                new Amphibian("Long-toed salamander","Ambystoma macrodactylum", 4, true),
                new Location("North America", "Earth", 100.1667, 48.1667),
                LocalDate.now(), "https://upload.wikimedia.org/wikipedia/commons/0/0a/Long-toedAutotomy.jpg", "slimey");
        nr4.getLocation().addBiom("Temperate rainforests");
        nr4.getLocation().addBiom("Coniferous forests");
        nr4.getLocation().addBiom("Montane riperian zones");
        nr4.getLocation().addBiom("Cheatgrass plains");

        Observation nr5 = new Observation(5, "The flying pest",10,
                new Invertebrate(" Fruit fly","Drosophila melanogaster", true, false,false, 6, 2),
                new Location("Everywhere", "All planets ever", 0.0, 0.0),
                LocalDate.now(), "https://upload.wikimedia.org/wikipedia/commons/7/7c/55542main_maflies_med.jpg", "While an area is infested, almost impossible to get rid of them.");
        nr5.getLocation().addBiom("Fermented fruits");

        Observation nr6 = new Observation(6, "Long legged devils",10,
                new Invertebrate("Arachnid","Arachnid", false, false,false, 8, 2),
                new Location("Everywhere", "Everywhere", 0.0, 0.0),
                LocalDate.now(), "https://upload.wikimedia.org/wikipedia/commons/0/0c/Arachnid_-_Guelph%2C_Ontario.jpg", "no no and no");
        nr6.getLocation().addBiom("Caves");
        nr6.getLocation().addBiom("Soil litter");
        nr6.getLocation().addBiom("Burrows");
        nr6.getLocation().addBiom("Basically everywhere");

        repository.addObservation(nr1);
        repository.addObservation(nr2);
        repository.addObservation(nr3);
        repository.addObservation(nr4);
        repository.addObservation(nr5);
        repository.addObservation(nr6);

        return repository;
    }

    private static void printObservations(ObservationRepository repository) {
        for (Observation observation : repository.getAllObservations()) {
            System.out.println(observation);
        }
    }
}
