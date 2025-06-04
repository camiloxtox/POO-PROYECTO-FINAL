package repository;

import model.Book;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BookRepository {
    private static final List<Book> books = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    public static List<Book> getAll() {
        return new ArrayList<>(books);
    }

    public static Optional<Book> getById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    public static Book add(Book book) {
        book.setId(autoId.getAndIncrement());
        books.add(book);
        return book;
    }

    public static Optional<Book> update(int id, Book updatedBook) {
        Optional<Book> existing = getById(id);
        existing.ifPresent(b -> {
            b.setTitle(updatedBook.getTitle());
            b.setAuthor(updatedBook.getAuthor());
            b.setYear(updatedBook.getYear()); // ✅ Ahora `year` existe en `Book.java`
        });
        return existing;
    }

    public static boolean delete(int id) {
        return books.removeIf(b -> b.getId() == id);
    }
}
