package repository;

import model.Notification;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotificationRepository {
    private static List<Notification> notifications = new ArrayList<>();

    // CREATE - Agregar notificación
    public static void addNotification(Notification notification) {
        notifications.add(notification);
    }

    // READ - Obtener todas las notificaciones
    public static List<Notification> getAllNotifications() {
        return notifications;
    }

    // READ - Obtener notificación por ID
    public static Optional<Notification> getNotificationById(int id) {
        return notifications.stream().filter(n -> n.getId() == id).findFirst();
    }

    // UPDATE - Marcar notificación como leída
    public static boolean markAsRead(int id) {
        Optional<Notification> notification = getNotificationById(id);
        if (notification.isPresent()) {
            notification.get().setRead(true);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar notificación por ID
    public static boolean deleteNotification(int id) {
        return notifications.removeIf(n -> n.getId() == id);
    }
}
