package repository;

import model.Department;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRepository {
    private static List<Department> departments = new ArrayList<>();

    // CREATE - Add department
    public static void addDepartment(Department department) {
        departments.add(department);
    }

    // READ - Get all departments
    public static List<Department> getAllDepartments() {
        return departments;
    }

    // READ - Get department by ID
    public static Optional<Department> getDepartmentById(int id) {
        return departments.stream().filter(d -> d.getId() == id).findFirst();
    }

    // UPDATE - Update department details
    public static boolean updateDepartment(int id, String newName, String newDescription, int newCityId) {
        Optional<Department> department = getDepartmentById(id);
        if (department.isPresent()) {
            department.get().setName(newName);
            department.get().setDescription(newDescription);
            department.get().setCityId(newCityId);
            return true;
        }
        return false;
    }

    // DELETE - Remove department by ID
    public static boolean deleteDepartment(int id) {
        return departments.removeIf(d -> d.getId() == id);
    }
}
