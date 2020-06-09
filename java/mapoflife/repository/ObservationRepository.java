package mapoflife.repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import mapoflife.model.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


public class ObservationRepository implements IObservationRepository{
    private String path;
    private ArrayList<Observation> observations = new ArrayList<>();

    public ObservationRepository(String path) {
        this.path = path;
        readFromJSON();
    }

    public void writeToJSONFromThread() {
        Thread newThread = new Thread(() -> writeToJSON());
        newThread.start();
    }

    public void writeToJSON() {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, observations);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            Observation[] observationsArray = objectMapper.readValue(new File(path), Observation[].class);

            observations.addAll(Arrays.asList(observationsArray));

        } catch (JsonParseException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Observation> getAllObservations() {
        return observations;
    }

    @Override
    public Observation getSpecificObservation(String observationId) {
        for (Observation observation : observations) {
                if (observation.getName().equals(observationId)){
                    return observation;
                }
            }
        return null;
    }

    public Species getSpesificSpecies(String observationId) {
        Observation wantedObservation = getSpecificObservation(observationId);
        Species wantedSpecies = wantedObservation.getSpecies();
        return wantedSpecies;
    }

    @Override
    public void addObservation(Observation observation) {
        observations.add(observation);
        writeToJSONFromThread();

    }

    @Override
    public void updateObservation(String oldName, Observation updatedObservation) {
        ArrayList<Observation> newList = new ArrayList<>();

        for (Observation observation : observations) {
            if (!(observation.getName().equals(oldName))) {
                newList.add(observation);
            }
        }
        newList.add(updatedObservation);

        observations = newList;

        System.out.println(newList);

        writeToJSONFromThread();
    }

    @Override
    public void deleteObservation(String observationToDelete) {
        Observation delete = getSpecificObservation(observationToDelete);
        observations.remove(delete);
        writeToJSONFromThread();
    }

}
