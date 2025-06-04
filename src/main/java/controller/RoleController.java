package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Role;
import repository.RoleRepository;
import java.util.List;
import java.util.Optional;

public class RoleController {
    public static void configureRoutes(Javalin app) {
        app.get("/roles", RoleController::getAllRoles);
        app.get("/roles/{id}", RoleController::getRoleById);
        app.post("/roles", RoleController::createRole);
        app.put("/roles/{id}", RoleController::updateRole);
        app.delete("/roles/{id}", RoleController::deleteRole);
    }

    public static void getAllRoles(Context ctx) {
        List<Role> roles = RoleRepository.getAllRoles();
        ctx.json(roles);
    }

    public static void getRoleById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Role> role = RoleRepository.getRoleById(id);
        role.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Role not found"));
    }

    public static void createRole(Context ctx) {
        Role newRole = ctx.bodyAsClass(Role.class);
        RoleRepository.addRole(newRole);
        ctx.status(201).json(newRole);
    }

    public static void updateRole(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Role roleData = ctx.bodyAsClass(Role.class);
        boolean updated = RoleRepository.updateRole(id, roleData.getName(), roleData.getDescription());
        if (updated) {
            ctx.result("Role successfully updated");
        } else {
            ctx.status(404).result("Role not found");
        }
    }

    public static void deleteRole(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = RoleRepository.deleteRole(id);
        if (deleted) {
            ctx.result("Role successfully deleted");
        } else {
            ctx.status(404).result("Role not found");
        }
    }
}
