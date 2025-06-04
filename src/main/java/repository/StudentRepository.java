package repository;

import model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private static List<Student> students = new ArrayList<>();

    // CREATE - Agregar estudiante
    public static void addStudent(Student student) {
        students.add(student);
    }

    // READ - Obtener todos los estudiantes
    public static List<Student> getAllStudents() {
        return students;
    }

    // READ - Obtener estudiante por ID
    public static Optional<Student> getStudentById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst();
    }

    // UPDATE - Actualizar información del estudiante
    public static boolean updateStudent(int id, String newName, String newEmail, int newAge, String newMajor) {
        Optional<Student> student = getStudentById(id);
        if (student.isPresent()) {
            student.get().setName(newName);
            student.get().setEmail(newEmail);
            student.get().setAge(newAge);
            student.get().setMajor(newMajor);
            return true;
        }
        return false;
    }

    // DELETE - Eliminar estudiante por ID
    public static boolean deleteStudent(int id) {
        return students.removeIf(s -> s.getId() == id);
    }
}
