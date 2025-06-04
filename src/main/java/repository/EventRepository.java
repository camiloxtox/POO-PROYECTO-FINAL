package repository;

import model.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventRepository {
    private static List<Event> events = new ArrayList<>();

    // CREATE - Add event
    public static void addEvent(Event event) {
        events.add(event);
    }

    // READ - Get all events
    public static List<Event> getAllEvents() {
        return events;
    }

    // READ - Get event by ID
    public static Optional<Event> getEventById(int id) {
        return events.stream().filter(e -> e.getId() == id).findFirst();
    }

    // UPDATE - Update event details
    public static boolean updateEvent(int id, String newName, String newDescription, String newLocation, int newOrganizerId) {
        Optional<Event> event = getEventById(id);
        if (event.isPresent()) {
            event.get().setName(newName);
            event.get().setDescription(newDescription);
            event.get().setLocation(newLocation);
            event.get().setOrganizerId(newOrganizerId);
            return true;
        }
        return false;
    }

    // DELETE - Remove event by ID
    public static boolean deleteEvent(int id) {
        return events.removeIf(e -> e.getId() == id);
    }
}
