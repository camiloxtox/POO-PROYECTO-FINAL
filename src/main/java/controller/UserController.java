package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.User;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserController {
    public static void configureRoutes(Javalin app) {
        app.get("/users", UserController::getAllUsers);
        app.get("/users/{id}", UserController::getUserById);
        app.post("/users", UserController::createUser);
        app.put("/users/{id}", UserController::updateUser);
        app.delete("/users/{id}", UserController::deleteUser);
    }

    public static void getAllUsers(Context ctx) {
        List<User> users = UserRepository.getAllUsers();
        ctx.json(users);
    }

    public static void getUserById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<User> user = UserRepository.getUserById(id);
        user.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("User not found"));
    }

    public static void createUser(Context ctx) {
        User newUser = ctx.bodyAsClass(User.class);
        UserRepository.addUser(newUser);
        ctx.status(201).json(newUser);
    }

    public static void updateUser(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        User userData = ctx.bodyAsClass(User.class);
        boolean updated = UserRepository.updateUser(id, userData.getName(), userData.getEmail());
        if (updated) {
            ctx.result("User successfully updated");
        } else {
            ctx.status(404).result("User not found");
        }
    }

    public static void deleteUser(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = UserRepository.deleteUser(id);
        if (deleted) {
            ctx.result("User successfully deleted");
        } else {
            ctx.status(404).result("User not found");
        }
    }
}
