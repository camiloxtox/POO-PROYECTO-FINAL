package repository;

import model.Reservation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationRepository {
    private static List<Reservation> reservations = new ArrayList<>();

    // CREATE - Agregar reserva
    public static void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    // READ - Obtener todas las reservas
    public static List<Reservation> getAllReservations() {
        return reservations;
    }

    // READ - Obtener reserva por ID
    public static Optional<Reservation> getReservationById(int id) {
        return reservations.stream().filter(r -> r.getId() == id).findFirst();
    }

    // UPDATE - Confirmar reserva
    public static boolean confirmReservation(int id) {
        Optional<Reservation> reservation = getReservationById(id);
        if (reservation.isPresent()) {
            reservation.get().setConfirmed(true);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar reserva por ID
    public static boolean deleteReservation(int id) {
        return reservations.removeIf(r -> r.getId() == id);
    }
}
