package mapoflife.controller;

import io.javalin.http.Context;
import mapoflife.model.*;
import mapoflife.repository.IObservationRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class ObservationController {
    private IObservationRepository observationRepository;

    public ObservationController(IObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }

    public void getAllObservations(Context ctx) {
        ArrayList<Observation> observations = observationRepository.getAllObservations();
        String sortBy = ctx.queryParam("sort_by");

        if (sortBy != null) {
            switch (sortBy) {
                case "id":
                    observations.sort(Comparator.comparingInt(Observation::getId));

                    break;
                case "name":
                    observations.sort(Comparator.comparing(Observation::getName));
                    break;
                case "planet":
                    observations.sort(Comparator.comparing(observation -> observation.getLocation().getPlanet()));
                    break;
                case "species":
                    observations.sort(Comparator.comparing(observation -> observation.getSpecies().getKindOfSpecies()));
                default:
                    break;
            }
        }

        ctx.json(observations);
    }

    public void getSpecificObservation(Context ctx) {
        String observationId = ctx.pathParam("observation-id");

        Observation wantedObservation = observationRepository.getSpecificObservation(observationId);

        ctx.json(wantedObservation);
    }

    public void getSpesificSpecies(Context ctx) {
        String observationId = ctx.pathParam("observation-id");
        Species wantedSpecies = observationRepository.getSpesificSpecies(observationId);
        ctx.json(wantedSpecies);

    }

    public void addObservation(Context ctx) {
        Observation newObservation = getObservationDetailsFromForm(ctx);
        System.out.println(newObservation);
        observationRepository.addObservation(newObservation);

        ctx.redirect("/observations");
    }

    public void updateObservation(Context ctx) {
        String oldName = ctx.pathParam("observation-id");
        Observation updatedObservation = getObservationDetailsFromForm(ctx);
        observationRepository.updateObservation(oldName, updatedObservation);

        ctx.redirect("/observations");
    }

    public void deleteObservation(Context ctx) {
        String observationName = ctx.pathParam("observation-id");
        observationRepository.deleteObservation(observationName);

        ctx.redirect("/observations");
    }

    private Observation getObservationDetailsFromForm(Context ctx) {
        int id = Integer.parseInt(ctx.formParam("id"));
        String name = ctx.formParam("name");
        int numberOfSpecimen = Integer.parseInt(ctx.formParam("numberOfSpecimen"));

        Species species = getSpeciesFromForm(ctx);

        Location location = getLocationFromForm(ctx);

        LocalDate dateOfObservation = LocalDate.parse(ctx.formParam("dateOfObservation"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String notes = ctx.formParam("notes");
        String pictureUrl = ctx.formParam("pictureUrl");


        Observation newObservation = new Observation(id, name,numberOfSpecimen, species, location, dateOfObservation, pictureUrl, notes);
        return newObservation;
    }

    private Species getSpeciesFromForm(Context ctx) {
        String selectedSpecies = ctx.formParam("selectedSpecies");

        String name = ctx.formParam("speciesName");
        String scientificName = ctx.formParam("speciesScientificName");


        if (selectedSpecies.equals("amphibian")) {
            int numberOfLegs = Integer.parseInt(ctx.formParam("numberOfLegs"));
            boolean isPoisonous = Boolean.parseBoolean(ctx.formParam("isPoisonous"));
            Amphibian newAmphibian = new Amphibian(name, scientificName, numberOfLegs, isPoisonous);
            return newAmphibian;
        }

        else if (selectedSpecies.equals("invertebrate")) {
            boolean canFly = Boolean.parseBoolean(ctx.formParam("canFly"));
            boolean isPoisonous = Boolean.parseBoolean(ctx.formParam("isPoisonous"));
            boolean canBreathUnderWater = Boolean.parseBoolean(ctx.formParam("canBreathUnderWater"));
            int numberOfLegs = Integer.parseInt(ctx.formParam("numberOfLegs"));
            int numberOfEyes = Integer.parseInt(ctx.formParam("numberOfEyes"));
            Invertebrate newInvertebrate = new Invertebrate(name, scientificName, canFly, isPoisonous, canBreathUnderWater, numberOfLegs, numberOfEyes);
            return newInvertebrate;

        }

        else if (selectedSpecies.equals("bird")) {
            boolean canFly = Boolean.parseBoolean(ctx.formParam("canFly"));
            boolean isPredator = Boolean.parseBoolean(ctx.formParam("isPredator"));
            double wingspan = Double.parseDouble(ctx.formParam("wingspan"));
            double weight = Double.parseDouble(ctx.formParam("weight"));
            Bird newBird = new Bird(name, scientificName, canFly, isPredator, wingspan, weight);
            return newBird;
        }

        return null;
    }

    private Location getLocationFromForm(Context ctx) {
        String name = ctx.formParam("locationName");
        String planet = ctx.formParam("planet");
        double longitude = Double.parseDouble(ctx.formParam("longitude"));
        double latitude = Double.parseDouble(ctx.formParam("latitude"));

        Location newLocation = new Location(name, planet, longitude, latitude);

        String biom = ctx.formParam("biom");
        String[] splitOnComma = biom.split(",");
        for (int x = 0; x < splitOnComma.length; x++) {
            newLocation.addBiom(splitOnComma[x]);
        }

        return newLocation;

    }

}
