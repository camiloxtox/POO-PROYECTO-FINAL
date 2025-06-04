package model;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private String name;
    private String description;
    private String location;
    private int organizerId;
    private LocalDateTime dateTime;  // ✅ Added field

    // ✅ Default Constructor (Needed for Javalin)
    public Event() {}

    // ✅ Parameterized Constructor
    public Event(int id, String name, String description, String location, int organizerId, LocalDateTime dateTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.organizerId = organizerId;
        this.dateTime = dateTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getOrganizerId() { return organizerId; }
    public void setOrganizerId(int organizerId) { this.organizerId = organizerId; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
}
