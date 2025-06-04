package model;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private int userId;
    private int resourceId;  // ID del recurso reservado (ej. libro, sala)
    private LocalDateTime reservationDate;
    private LocalDateTime expirationDate;
    private boolean confirmed;

    public Reservation() {}

    public Reservation(int id, int userId, int resourceId, LocalDateTime reservationDate, LocalDateTime expirationDate, boolean confirmed) {
        this.id = id;
        this.userId = userId;
        this.resourceId = resourceId;
        this.reservationDate = reservationDate;
        this.expirationDate = expirationDate;
        this.confirmed = confirmed;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getResourceId() { return resourceId; }
    public void setResourceId(int resourceId) { this.resourceId = resourceId; }

    public LocalDateTime getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }

    public LocalDateTime getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDateTime expirationDate) { this.expirationDate = expirationDate; }

    public boolean isConfirmed() { return confirmed; }
    public void setConfirmed(boolean confirmed) { this.confirmed = confirmed; }
}
