package repository;

import model.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageRepository {
    private static List<Message> messages = new ArrayList<>();

    // CREATE - Agregar mensaje
    public static void addMessage(Message message) {
        messages.add(message);
    }

    // READ - Obtener todos los mensajes
    public static List<Message> getAllMessages() {
        return messages;
    }

    // READ - Obtener mensaje por ID
    public static Optional<Message> getMessageById(int id) {
        return messages.stream().filter(m -> m.getId() == id).findFirst();
    }

    // UPDATE - Actualizar mensaje
    public static boolean updateMessage(int id, String newContent) {
        Optional<Message> message = getMessageById(id);
        if (message.isPresent()) {
            message.get().setContent(newContent);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar mensaje por ID
    public static boolean deleteMessage(int id) {
        return messages.removeIf(m -> m.getId() == id);
    }
}
