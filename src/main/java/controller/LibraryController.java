package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Library;
import repository.LibraryRepository;
import java.util.List;
import java.util.Optional;

public class LibraryController {
    public static void configureRoutes(Javalin app) {
        app.get("/libraries", LibraryController::getAllLibraries);
        app.get("/libraries/{id}", LibraryController::getLibraryById);
        app.post("/libraries", LibraryController::createLibrary);
        app.put("/libraries/{id}", LibraryController::updateLibrary);
        app.delete("/libraries/{id}", LibraryController::deleteLibrary);
    }

    public static void getAllLibraries(Context ctx) {
        List<Library> libraries = LibraryRepository.getAllLibraries();
        ctx.json(libraries);
    }

    public static void getLibraryById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Library> library = LibraryRepository.getLibraryById(id);
        library.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Library not found"));
    }

    public static void createLibrary(Context ctx) {
        Library newLibrary = ctx.bodyAsClass(Library.class);
        LibraryRepository.addLibrary(newLibrary);
        ctx.status(201).json(newLibrary);
    }

    public static void updateLibrary(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Library libraryData = ctx.bodyAsClass(Library.class);
        boolean updated = LibraryRepository.updateLibrary(id, libraryData.getName(), libraryData.getAddress(), libraryData.getCityId());
        if (updated) {
            ctx.result("Library successfully updated");
        } else {
            ctx.status(404).result("Library not found");
        }
    }

    public static void deleteLibrary(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = LibraryRepository.deleteLibrary(id);
        if (deleted) {
            ctx.result("Library successfully deleted");
        } else {
            ctx.status(404).result("Library not found");
        }
    }
}
