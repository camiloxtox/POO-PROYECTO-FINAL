package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Author;
import repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

public class AuthorController {
    public static void configureRoutes(Javalin app) {
        app.get("/authors", AuthorController::getAllAuthors);
        app.get("/authors/{id}", AuthorController::getAuthorById);
        app.post("/authors", AuthorController::createAuthor);
        app.put("/authors/{id}", AuthorController::updateAuthor);
        app.delete("/authors/{id}", AuthorController::deleteAuthor);
    }

    public static void getAllAuthors(Context ctx) {
        List<Author> authors = AuthorRepository.getAllAuthors();
        ctx.json(authors);
    }

    public static void getAuthorById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Author> author = AuthorRepository.getAuthorById(id);
        author.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Author not found"));
    }

    public static void createAuthor(Context ctx) {
        Author newAuthor = ctx.bodyAsClass(Author.class);
        AuthorRepository.addAuthor(newAuthor);
        ctx.status(201).json(newAuthor);
    }

    public static void updateAuthor(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Author authorData = ctx.bodyAsClass(Author.class);
        boolean updated = AuthorRepository.updateAuthor(id, authorData.getName(), authorData.getBiography());
        if (updated) {
            ctx.result("Author successfully updated");
        } else {
            ctx.status(404).result("Author not found");
        }
    }

    public static void deleteAuthor(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = AuthorRepository.deleteAuthor(id);
        if (deleted) {
            ctx.result("Author successfully deleted");
        } else {
            ctx.status(404).result("Author not found");
        }
    }
}
