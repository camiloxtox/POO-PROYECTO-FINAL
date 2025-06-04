package repository;

import model.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PermissionRepository {
    private static List<Permission> permissions = new ArrayList<>();

    // CREATE - Agregar permiso
    public static void addPermission(Permission permission) {
        permissions.add(permission);
    }

    // READ - Obtener todos los permisos
    public static List<Permission> getAllPermissions() {
        return permissions;
    }

    // READ - Obtener permiso por ID
    public static Optional<Permission> getPermissionById(int id) {
        return permissions.stream().filter(p -> p.getId() == id).findFirst();
    }

    // UPDATE - Actualizar nombre y descripción de permiso
    public static boolean updatePermission(int id, String newName, String newDescription) {
        Optional<Permission> permission = getPermissionById(id);
        if (permission.isPresent()) {
            permission.get().setName(newName);
            permission.get().setDescription(newDescription);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar permiso por ID
    public static boolean deletePermission(int id) {
        return permissions.removeIf(p -> p.getId() == id);
    }
}
