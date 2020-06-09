package mapoflife;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;
import mapoflife.controller.ObservationController;
import mapoflife.repository.ObservationRepository;

public class MapOfLife {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start();
        app.config.enableWebjars();

        // repository and controllers
        ObservationRepository repository = new ObservationRepository("observations.json");
        ObservationController observationController = new ObservationController(repository);

        // views
        app.get("/", ctx -> ctx.redirect("/observations"));
        app.get("/observations", new VueComponent("observations-overview"));
        app.get("/observations/:observation-id", new VueComponent("observation-detail"));
        app.get("/observations/:observation-id/add", new VueComponent("observation-add"));
        app.get("/observations/:observation-id/update", new VueComponent("observation-update"));
        app.get("/observations/:observation-id/species/:species-id", new VueComponent("species-detail"));


        // api
        app.get("/api/observations", observationController::getAllObservations);
        app.get("/api/observations/:observation-id", observationController::getSpecificObservation);
        app.get("/api/observations/:observation-id/species/:species-id", observationController::getSpesificSpecies);
        app.post("/api/observations/add", observationController::addObservation);
        app.post("/api/observations/:observation-id/update", observationController::updateObservation);
        app.get("/api/observations/:observation-id/delete", observationController::deleteObservation);

    }
}
