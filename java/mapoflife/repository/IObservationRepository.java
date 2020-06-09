package mapoflife.repository;

import mapoflife.model.Observation;
import mapoflife.model.Species;

import java.util.ArrayList;

public interface IObservationRepository {
    ArrayList<Observation> getAllObservations();
    Observation getSpecificObservation(String observationId);
    Species getSpesificSpecies(String observationId);
    void addObservation(Observation observation);
    void updateObservation(String oldName, Observation observation);
    void deleteObservation(String observation);
}
