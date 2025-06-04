package repository;

import model.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherRepository {
    private static List<Teacher> teachers = new ArrayList<>();

    // CREATE - Agregar profesor
    public static void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    // READ - Obtener todos los profesores
    public static List<Teacher> getAllTeachers() {
        return teachers;
    }

    // READ - Obtener profesor por ID
    public static Optional<Teacher> getTeacherById(int id) {
        return teachers.stream().filter(t -> t.getId() == id).findFirst();
    }

    // UPDATE - Actualizar información del profesor
    public static boolean updateTeacher(int id, String newName, String newEmail, String newSubject, int newExperienceYears) {
        Optional<Teacher> teacher = getTeacherById(id);
        if (teacher.isPresent()) {
            teacher.get().setName(newName);
            teacher.get().setEmail(newEmail);
            teacher.get().setSubject(newSubject);
            teacher.get().setExperienceYears(newExperienceYears);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar profesor por ID
    public static boolean deleteTeacher(int id) {
        return teachers.removeIf(t -> t.getId() == id);
    }
}
