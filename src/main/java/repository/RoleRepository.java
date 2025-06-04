package repository;

import model.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepository {
    private static List<Role> roles = new ArrayList<>();

    // CREATE - Agregar rol
    public static void addRole(Role role) {
        roles.add(role);
    }

    // READ - Obtener todos los roles
    public static List<Role> getAllRoles() {
        return roles;
    }

    // READ - Obtener rol por ID
    public static Optional<Role> getRoleById(int id) {
        return roles.stream().filter(r -> r.getId() == id).findFirst();
    }

    // UPDATE - Actualizar nombre y descripción de rol
    public static boolean updateRole(int id, String newName, String newDescription) {
        Optional<Role> role = getRoleById(id);
        if (role.isPresent()) {
            role.get().setName(newName);
            role.get().setDescription(newDescription);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar rol por ID
    public static boolean deleteRole(int id) {
        return roles.removeIf(r -> r.getId() == id);
    }
}
