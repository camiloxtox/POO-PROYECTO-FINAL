package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Event;
import repository.EventRepository;
import java.util.List;
import java.util.Optional;

public class EventController {
    public static void configureRoutes(Javalin app) {
        app.get("/events", EventController::getAllEvents);
        app.get("/events/{id}", EventController::getEventById);
        app.post("/events", EventController::createEvent);
        app.put("/events/{id}", EventController::updateEvent);
        app.delete("/events/{id}", EventController::deleteEvent);
    }

    public static void getAllEvents(Context ctx) {
        List<Event> events = EventRepository.getAllEvents();
        ctx.json(events);
    }

    public static void getEventById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Event> event = EventRepository.getEventById(id);
        event.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Event not found"));
    }

    public static void createEvent(Context ctx) {
        Event newEvent = ctx.bodyAsClass(Event.class);
        EventRepository.addEvent(newEvent);
        ctx.status(201).json(newEvent);
    }

    public static void updateEvent(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Event eventData = ctx.bodyAsClass(Event.class);
        boolean updated = EventRepository.updateEvent(id, eventData.getName(), eventData.getDescription(), eventData.getLocation(), eventData.getOrganizerId());
        if (updated) {
            ctx.result("Event successfully updated");
        } else {
            ctx.status(404).result("Event not found");
        }
    }

    public static void deleteEvent(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = EventRepository.deleteEvent(id);
        if (deleted) {
            ctx.result("Event successfully deleted");
        } else {
            ctx.status(404).result("Event not found");
        }
    }
}
