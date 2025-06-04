package model;

import java.time.LocalDateTime;

public class Notification {
    private int id;
    private int userId;
    private String message;
    private boolean read;
    private LocalDateTime timestamp;

    public Notification() {}

    public Notification(int id, int userId, String message, boolean read, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.read = read;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
