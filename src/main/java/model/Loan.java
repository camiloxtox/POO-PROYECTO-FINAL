package model;

import java.time.LocalDate;

public class Loan {
    private int id;
    private int bookId;      // ID del libro prestado
    private int userId;      // ID del usuario que toma el préstamo
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean returned; // Estado del préstamo

    public Loan() {}

    public Loan(int id, int bookId, int userId, LocalDate loanDate, LocalDate returnDate, boolean returned) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.returned = returned;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }
}
