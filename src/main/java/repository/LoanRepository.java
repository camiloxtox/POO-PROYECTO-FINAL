package repository;

import model.Loan;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanRepository {
    private static List<Loan> loans = new ArrayList<>();

    // CREATE - Agregar préstamo
    public static void addLoan(Loan loan) {
        loans.add(loan);
    }

    // READ - Obtener todos los préstamos
    public static List<Loan> getAllLoans() {
        return loans;
    }

    // READ - Obtener préstamo por ID
    public static Optional<Loan> getLoanById(int id) {
        return loans.stream().filter(l -> l.getId() == id).findFirst();
    }

    // UPDATE - Actualizar préstamo
    public static boolean updateLoan(int id, int bookId, int userId, boolean returned) {
        Optional<Loan> loan = getLoanById(id);
        if (loan.isPresent()) {
            loan.get().setBookId(bookId);
            loan.get().setUserId(userId);
            loan.get().setReturned(returned);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar préstamo por ID
    public static boolean deleteLoan(int id) {
        return loans.removeIf(l -> l.getId() == id);
    }
}
