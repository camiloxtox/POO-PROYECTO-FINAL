package repository;

import model.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepository {
    private static List<Category> categories = new ArrayList<>();

    public static void addCategory(Category category) { categories.add(category); }
    public static List<Category> getAllCategories() { return categories; }
    public static Optional<Category> getCategoryById(int id) { return categories.stream().filter(c -> c.getId() == id).findFirst(); }
    public static boolean updateCategory(int id, String newName, String newDescription) {
        Optional<Category> category = getCategoryById(id);
        if (category.isPresent()) {
            category.get().setName(newName);
            category.get().setDescription(newDescription);
            return true;
        }
        return false;
    }
    public static boolean deleteCategory(int id) { return categories.removeIf(c -> c.getId() == id); }
}
