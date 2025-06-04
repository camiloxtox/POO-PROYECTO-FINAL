package repository;

import model.Author;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorRepository {
    private static List<Author> authors = new ArrayList<>();

    // CREATE - Add author
    public static void addAuthor(Author author) {
        authors.add(author);
    }

    // READ - Get all authors
    public static List<Author> getAllAuthors() {
        return authors;
    }

    // READ - Get author by ID
    public static Optional<Author> getAuthorById(int id) {
        return authors.stream().filter(a -> a.getId() == id).findFirst();
    }

    // UPDATE - Update author details
    public static boolean updateAuthor(int id, String newName, String newBiography) {
        Optional<Author> author = getAuthorById(id);
        if (author.isPresent()) {
            author.get().setName(newName);
            author.get().setBiography(newBiography);
            return true;
        }
        return false;
    }

    // DELETE - Remove author by ID
    public static boolean deleteAuthor(int id) {
        return authors.removeIf(a -> a.getId() == id);
    }
}
