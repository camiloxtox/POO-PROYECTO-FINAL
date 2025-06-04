package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Loan;
import repository.LoanRepository;
import java.util.List;
import java.util.Optional;

public class LoanController {
    public static void configureRoutes(Javalin app) {
        app.get("/loans", LoanController::getAllLoans);
        app.get("/loans/{id}", LoanController::getLoanById);
        app.post("/loans", LoanController::createLoan);
        app.put("/loans/{id}", LoanController::updateLoan);
        app.delete("/loans/{id}", LoanController::deleteLoan);
    }

    public static void getAllLoans(Context ctx) {
        List<Loan> loans = LoanRepository.getAllLoans();
        ctx.json(loans);
    }

    public static void getLoanById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Loan> loan = LoanRepository.getLoanById(id);
        loan.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Loan not found"));
    }

    public static void createLoan(Context ctx) {
        Loan newLoan = ctx.bodyAsClass(Loan.class);
        LoanRepository.addLoan(newLoan);
        ctx.status(201).json(newLoan);
    }

    public static void updateLoan(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Loan loanData = ctx.bodyAsClass(Loan.class);
        boolean updated = LoanRepository.updateLoan(id, loanData.getBookId(), loanData.getUserId(), loanData.isReturned());
        if (updated) {
            ctx.result("Loan successfully updated");
        } else {
            ctx.status(404).result("Loan not found");
        }
    }

    public static void deleteLoan(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = LoanRepository.deleteLoan(id);
        if (deleted) {
            ctx.result("Loan successfully deleted");
        } else {
            ctx.status(404).result("Loan not found");
        }
    }
}
