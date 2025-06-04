package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Teacher;
import repository.TeacherRepository;
import java.util.List;
import java.util.Optional;

public class TeacherController {
    public static void configureRoutes(Javalin app) {
        app.get("/teachers", TeacherController::getAllTeachers);
        app.get("/teachers/{id}", TeacherController::getTeacherById);
        app.post("/teachers", TeacherController::createTeacher);
        app.put("/teachers/{id}", TeacherController::updateTeacher);
        app.delete("/teachers/{id}", TeacherController::deleteTeacher);
    }

    public static void getAllTeachers(Context ctx) {
        List<Teacher> teachers = TeacherRepository.getAllTeachers();
        ctx.json(teachers);
    }

    public static void getTeacherById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Teacher> teacher = TeacherRepository.getTeacherById(id);
        teacher.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Teacher not found"));
    }

    public static void createTeacher(Context ctx) {
        Teacher newTeacher = ctx.bodyAsClass(Teacher.class);
        TeacherRepository.addTeacher(newTeacher);
        ctx.status(201).json(newTeacher);
    }

    public static void updateTeacher(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Teacher teacherData = ctx.bodyAsClass(Teacher.class);
        boolean updated = TeacherRepository.updateTeacher(id, teacherData.getName(), teacherData.getEmail(), teacherData.getSubject(), teacherData.getExperienceYears());
        if (updated) {
            ctx.result("Teacher successfully updated");
        } else {
            ctx.status(404).result("Teacher not found");
        }
    }

    public static void deleteTeacher(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = TeacherRepository.deleteTeacher(id);
        if (deleted) {
            ctx.result("Teacher successfully deleted");
        } else {
            ctx.status(404).result("Teacher not found");
        }
    }
}
