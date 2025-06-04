package repository;

import model.Payment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentRepository {
    private static List<Payment> payments = new ArrayList<>();

    // CREATE - Agregar pago
    public static void addPayment(Payment payment) {
        payments.add(payment);
    }

    // READ - Obtener todos los pagos
    public static List<Payment> getAllPayments() {
        return payments;
    }

    // READ - Obtener pago por ID
    public static Optional<Payment> getPaymentById(int id) {
        return payments.stream().filter(p -> p.getId() == id).findFirst();
    }

    // UPDATE - Confirmar pago
    public static boolean confirmPayment(int id) {
        Optional<Payment> payment = getPaymentById(id);
        if (payment.isPresent()) {
            payment.get().setConfirmed(true);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar pago por ID
    public static boolean deletePayment(int id) {
        return payments.removeIf(p -> p.getId() == id);
    }
}
