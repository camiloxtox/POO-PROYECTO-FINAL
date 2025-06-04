package controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.Book;
import repository.BookRepository;

import java.util.List;
import java.util.Optional;

public class BookController {
    public static void configureRoutes(Javalin app) {
        app.get("/books", BookController::getAllBooks);
        app.get("/books/{id}", BookController::getBookById);
        app.post("/books", BookController::createBook);
        app.put("/books/{id}", BookController::updateBook);
        app.delete("/books/{id}", BookController::deleteBook);
    }

    public static void getAllBooks(Context ctx) {
        List<Book> books = BookRepository.getAllBooks();
        ctx.json(books);
    }

    public static void getBookById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Book> book = BookRepository.getBookById(id);
        book.ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Book not found"));
    }

    public static void createBook(Context ctx) {
        Book newBook = ctx.bodyAsClass(Book.class);
        BookRepository.addBook(newBook);
        ctx.status(201).json(newBook);
    }

    public static void updateBook(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Book bookData = ctx.bodyAsClass(Book.class);
        boolean updated = BookRepository.updateBook(id, bookData.getTitle(), bookData.getAuthor(), bookData.getIsbn());
        if (updated) {
            ctx.result("Book successfully updated");
        } else {
            ctx.status(404).result("Book not found");
        }
    }

    public static void deleteBook(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = BookRepository.deleteBook(id);
        if (deleted) {
            ctx.result("Book successfully deleted");
        } else {
            ctx.status(404).result("Book not found");
        }
    }
}
