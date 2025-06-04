package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Publisher;
import repository.PublisherRepository;
import java.util.List;
import java.util.Optional;

public class PublisherController {
    public static void configureRoutes(Javalin app) {
        app.get("/publishers", PublisherController::getAllPublishers);
        app.get("/publishers/{id}", PublisherController::getPublisherById);
        app.post("/publishers", PublisherController::createPublisher);
        app.put("/publishers/{id}", PublisherController::updatePublisher);
        app.delete("/publishers/{id}", PublisherController::deletePublisher);
    }

    public static void getAllPublishers(Context ctx) {
        List<Publisher> publishers = PublisherRepository.getAllPublishers();
        ctx.json(publishers);
    }

    public static void getPublisherById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Publisher> publisher = PublisherRepository.getPublisherById(id);
        publisher.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Publisher not found"));
    }

    public static void createPublisher(Context ctx) {
        Publisher newPublisher = ctx.bodyAsClass(Publisher.class);
        PublisherRepository.addPublisher(newPublisher);
        ctx.status(201).json(newPublisher);
    }

    public static void updatePublisher(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Publisher publisherData = ctx.bodyAsClass(Publisher.class);
        boolean updated = PublisherRepository.updatePublisher(id, publisherData.getName(), publisherData.getCountry());
        if (updated) {
            ctx.result("Publisher successfully updated");
        } else {
            ctx.status(404).result("Publisher not found");
        }
    }

    public static void deletePublisher(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = PublisherRepository.deletePublisher(id);
        if (deleted) {
            ctx.result("Publisher successfully deleted");
        } else {
            ctx.status(404).result("Publisher not found");
        }
    }
}
