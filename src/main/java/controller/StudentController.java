package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Student;
import repository.StudentRepository;
import java.util.List;
import java.util.Optional;

public class StudentController {
    public static void configureRoutes(Javalin app) {
        app.get("/students", StudentController::getAllStudents);
        app.get("/students/{id}", StudentController::getStudentById);
        app.post("/students", StudentController::createStudent);
        app.put("/students/{id}", StudentController::updateStudent);
        app.delete("/students/{id}", StudentController::deleteStudent);
    }

    public static void getAllStudents(Context ctx) {
        List<Student> students = StudentRepository.getAllStudents();
        ctx.json(students);
    }

    public static void getStudentById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Student> student = StudentRepository.getStudentById(id);
        student.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Student not found"));
    }

    public static void createStudent(Context ctx) {
        Student newStudent = ctx.bodyAsClass(Student.class);
        StudentRepository.addStudent(newStudent);
        ctx.status(201).json(newStudent);
    }

    public static void updateStudent(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Student studentData = ctx.bodyAsClass(Student.class);
        boolean updated = StudentRepository.updateStudent(id, studentData.getName(), studentData.getEmail(), studentData.getAge(), studentData.getMajor());
        if (updated) {
            ctx.result("Student successfully updated");
        } else {
            ctx.status(404).result("Student not found");
        }
    }

    public static void deleteStudent(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = StudentRepository.deleteStudent(id);
        if (deleted) {
            ctx.result("Student successfully deleted");
        } else {
            ctx.status(404).result("Student not found");
        }
    }
}
