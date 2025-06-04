package model;

import java.time.LocalDateTime;

public class Review {
    private int id;
    private int userId;
    private int itemId;  // ID del libro, producto o servicio reseñado
    private String content;
    private int rating;  // Puntuación de 1 a 5
    private LocalDateTime timestamp;

    public Review() {}

    public Review(int id, int userId, int itemId, String content, int rating, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.content = content;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
