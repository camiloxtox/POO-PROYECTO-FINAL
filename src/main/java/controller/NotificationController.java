package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Notification;
import repository.NotificationRepository;
import java.util.List;
import java.util.Optional;

public class NotificationController {
    public static void configureRoutes(Javalin app) {
        app.get("/notifications", NotificationController::getAllNotifications);
        app.get("/notifications/{id}", NotificationController::getNotificationById);
        app.post("/notifications", NotificationController::createNotification);
        app.put("/notifications/{id}", NotificationController::markNotificationAsRead);
        app.delete("/notifications/{id}", NotificationController::deleteNotification);
    }

    public static void getAllNotifications(Context ctx) {
        List<Notification> notifications = NotificationRepository.getAllNotifications();
        ctx.json(notifications);
    }

    public static void getNotificationById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Notification> notification = NotificationRepository.getNotificationById(id);
        notification.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Notification not found"));
    }

    public static void createNotification(Context ctx) {
        Notification newNotification = ctx.bodyAsClass(Notification.class);
        NotificationRepository.addNotification(newNotification);
        ctx.status(201).json(newNotification);
    }

    public static void markNotificationAsRead(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean updated = NotificationRepository.markAsRead(id);
        if (updated) {
            ctx.result("Notification marked as read");
        } else {
            ctx.status(404).result("Notification not found");
        }
    }

    public static void deleteNotification(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = NotificationRepository.deleteNotification(id);
        if (deleted) {
            ctx.result("Notification successfully deleted");
        } else {
            ctx.status(404).result("Notification not found");
        }
    }
}
