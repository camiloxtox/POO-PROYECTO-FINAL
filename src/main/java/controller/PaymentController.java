package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Payment;
import repository.PaymentRepository;
import java.util.List;
import java.util.Optional;

public class PaymentController {
    public static void configureRoutes(Javalin app) {
        app.get("/payments", PaymentController::getAllPayments);
        app.get("/payments/{id}", PaymentController::getPaymentById);
        app.post("/payments", PaymentController::createPayment);
        app.put("/payments/{id}", PaymentController::confirmPayment);
        app.delete("/payments/{id}", PaymentController::deletePayment);
    }

    public static void getAllPayments(Context ctx) {
        List<Payment> payments = PaymentRepository.getAllPayments();
        ctx.json(payments);
    }

    public static void getPaymentById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Payment> payment = PaymentRepository.getPaymentById(id);
        payment.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Payment not found"));
    }

    public static void createPayment(Context ctx) {
        Payment newPayment = ctx.bodyAsClass(Payment.class);
        PaymentRepository.addPayment(newPayment);
        ctx.status(201).json(newPayment);
    }

    public static void confirmPayment(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean updated = PaymentRepository.confirmPayment(id);
        if (updated) {
            ctx.result("Payment successfully confirmed");
        } else {
            ctx.status(404).result("Payment not found");
        }
    }

    public static void deletePayment(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = PaymentRepository.deletePayment(id);
        if (deleted) {
            ctx.result("Payment successfully deleted");
        } else {
            ctx.status(404).result("Payment not found");
        }
    }
}
