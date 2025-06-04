package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Department;
import repository.DepartmentRepository;
import java.util.List;
import java.util.Optional;

public class DepartmentController {
    public static void configureRoutes(Javalin app) {
        app.get("/departments", DepartmentController::getAllDepartments);
        app.get("/departments/{id}", DepartmentController::getDepartmentById);
        app.post("/departments", DepartmentController::createDepartment);
        app.put("/departments/{id}", DepartmentController::updateDepartment);
        app.delete("/departments/{id}", DepartmentController::deleteDepartment);
    }

    public static void getAllDepartments(Context ctx) {
        List<Department> departments = DepartmentRepository.getAllDepartments();
        ctx.json(departments);
    }

    public static void getDepartmentById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Department> department = DepartmentRepository.getDepartmentById(id);
        department.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Department not found"));
    }

    public static void createDepartment(Context ctx) {
        Department newDepartment = ctx.bodyAsClass(Department.class);
        DepartmentRepository.addDepartment(newDepartment);
        ctx.status(201).json(newDepartment);
    }

    public static void updateDepartment(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Department departmentData = ctx.bodyAsClass(Department.class);
        boolean updated = DepartmentRepository.updateDepartment(id, departmentData.getName(), departmentData.getDescription(), departmentData.getCityId());
        if (updated) {
            ctx.result("Department successfully updated");
        } else {
            ctx.status(404).result("Department not found");
        }
    }

    public static void deleteDepartment(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = DepartmentRepository.deleteDepartment(id);
        if (deleted) {
            ctx.result("Department successfully deleted");
        } else {
            ctx.status(404).result("Department not found");
        }
    }
}
