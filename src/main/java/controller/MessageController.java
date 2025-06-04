package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Message;
import repository.MessageRepository;
import java.util.List;
import java.util.Optional;

public class MessageController {
    public static void configureRoutes(Javalin app) {
        app.get("/messages", MessageController::getAllMessages);
        app.get("/messages/{id}", MessageController::getMessageById);
        app.post("/messages", MessageController::createMessage);
        app.put("/messages/{id}", MessageController::updateMessage);
        app.delete("/messages/{id}", MessageController::deleteMessage);
    }

    public static void getAllMessages(Context ctx) {
        List<Message> messages = MessageRepository.getAllMessages();
        ctx.json(messages);
    }

    public static void getMessageById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Message> message = MessageRepository.getMessageById(id);
        message.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Message not found"));
    }

    public static void createMessage(Context ctx) {
        Message newMessage = ctx.bodyAsClass(Message.class);
        MessageRepository.addMessage(newMessage);
        ctx.status(201).json(newMessage);
    }

    public static void updateMessage(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Message messageData = ctx.bodyAsClass(Message.class);
        boolean updated = MessageRepository.updateMessage(id, messageData.getContent());
        if (updated) {
            ctx.result("Message successfully updated");
        } else {
            ctx.status(404).result("Message not found");
        }
    }

    public static void deleteMessage(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = MessageRepository.deleteMessage(id);
        if (deleted) {
            ctx.result("Message successfully deleted");
        } else {
            ctx.status(404).result("Message not found");
        }
    }
}
