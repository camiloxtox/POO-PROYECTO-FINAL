package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Reservation;
import repository.ReservationRepository;
import java.util.List;
import java.util.Optional;

public class ReservationController {
    public static void configureRoutes(Javalin app) {
        app.get("/reservations", ReservationController::getAllReservations);
        app.get("/reservations/{id}", ReservationController::getReservationById);
        app.post("/reservations", ReservationController::createReservation);
        app.put("/reservations/{id}", ReservationController::confirmReservation);
        app.delete("/reservations/{id}", ReservationController::deleteReservation);
    }

    public static void getAllReservations(Context ctx) {
        List<Reservation> reservations = ReservationRepository.getAllReservations();
        ctx.json(reservations);
    }

    public static void getReservationById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Reservation> reservation = ReservationRepository.getReservationById(id);
        reservation.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Reservation not found"));
    }

    public static void createReservation(Context ctx) {
        Reservation newReservation = ctx.bodyAsClass(Reservation.class);
        ReservationRepository.addReservation(newReservation);
        ctx.status(201).json(newReservation);
    }

    public static void confirmReservation(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean updated = ReservationRepository.confirmReservation(id);
        if (updated) {
            ctx.result("Reservation successfully confirmed");
        } else {
            ctx.status(404).result("Reservation not found");
        }
    }

    public static void deleteReservation(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = ReservationRepository.deleteReservation(id);
        if (deleted) {
            ctx.result("Reservation successfully deleted");
        } else {
            ctx.status(404).result("Reservation not found");
        }
    }
}
