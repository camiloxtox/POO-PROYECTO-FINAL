package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Permission;
import repository.PermissionRepository;
import java.util.List;
import java.util.Optional;

public class PermissionController {
    public static void configureRoutes(Javalin app) {
        app.get("/permissions", PermissionController::getAllPermissions);
        app.get("/permissions/{id}", PermissionController::getPermissionById);
        app.post("/permissions", PermissionController::createPermission);
        app.put("/permissions/{id}", PermissionController::updatePermission);
        app.delete("/permissions/{id}", PermissionController::deletePermission);
    }

    public static void getAllPermissions(Context ctx) {
        List<Permission> permissions = PermissionRepository.getAllPermissions();
        ctx.json(permissions);
    }

    public static void getPermissionById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Permission> permission = PermissionRepository.getPermissionById(id);
        permission.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Permission not found"));
    }

    public static void createPermission(Context ctx) {
        Permission newPermission = ctx.bodyAsClass(Permission.class);
        PermissionRepository.addPermission(newPermission);
        ctx.status(201).json(newPermission);
    }

    public static void updatePermission(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Permission permissionData = ctx.bodyAsClass(Permission.class);
        boolean updated = PermissionRepository.updatePermission(id, permissionData.getName(), permissionData.getDescription());
        if (updated) {
            ctx.result("Permission successfully updated");
        } else {
            ctx.status(404).result("Permission not found");
        }
    }

    public static void deletePermission(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = PermissionRepository.deletePermission(id);
        if (deleted) {
            ctx.result("Permission successfully deleted");
        } else {
            ctx.status(404).result("Permission not found");
        }
    }
}
