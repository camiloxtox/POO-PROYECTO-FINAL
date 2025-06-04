package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Category;
import repository.CategoryRepository;
import java.util.List;
import java.util.Optional;

public class CategoryController {
    public static void configureRoutes(Javalin app) {
        app.get("/categories", CategoryController::getAllCategories);
        app.get("/categories/{id}", CategoryController::getCategoryById);
        app.post("/categories", CategoryController::createCategory);
        app.put("/categories/{id}", CategoryController::updateCategory);
        app.delete("/categories/{id}", CategoryController::deleteCategory);
    }

    public static void getAllCategories(Context ctx) { ctx.json(CategoryRepository.getAllCategories()); }
    public static void getCategoryById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Category> category = CategoryRepository.getCategoryById(id);
        category.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Category not found"));
    }
    public static void createCategory(Context ctx) {
        Category newCategory = ctx.bodyAsClass(Category.class);
        CategoryRepository.addCategory(newCategory);
        ctx.status(201).json(newCategory);
    }
    public static void updateCategory(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Category categoryData = ctx.bodyAsClass(Category.class);
        boolean updated = CategoryRepository.updateCategory(id, categoryData.getName(), categoryData.getDescription());
        if (updated) { ctx.result("Category successfully updated"); }
        else { ctx.status(404).result("Category not found"); }
    }
    public static void deleteCategory(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = CategoryRepository.deleteCategory(id);
        if (deleted) { ctx.result("Category successfully deleted"); }
        else { ctx.status(404).result("Category not found"); }
    }
}
