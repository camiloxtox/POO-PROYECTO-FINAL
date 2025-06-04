package model;

import java.time.LocalDateTime;

public class Payment {
    private int id;
    private int userId;
    private double amount;
    private String method; // Método de pago (ej. "Credit Card", "PayPal")
    private boolean confirmed;
    private LocalDateTime timestamp;

    public Payment() {}

    public Payment(int id, int userId, double amount, String method, boolean confirmed, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.method = method;
        this.confirmed = confirmed;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public boolean isConfirmed() { return confirmed; }
    public void setConfirmed(boolean confirmed) { this.confirmed = confirmed; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
