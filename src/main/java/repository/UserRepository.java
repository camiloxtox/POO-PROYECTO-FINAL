package repository;

import model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static List<User> users = new ArrayList<>();

    // CREATE - Agregar usuario
    public static void addUser(User user) {
        users.add(user);
    }

    // READ - Obtener todos los usuarios
    public static List<User> getAllUsers() {
        return users;
    }

    // READ - Obtener usuario por ID
    public static Optional<User> getUserById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    // UPDATE - Actualizar información del usuario
    public static boolean updateUser(int id, String newName, String newEmail) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            user.get().setName(newName);
            user.get().setEmail(newEmail);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar usuario por ID
    public static boolean deleteUser(int id) {
        return users.removeIf(u -> u.getId() == id);
    }
}
