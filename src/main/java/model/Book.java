package model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int categoryId;

    public Book() {}

    public Book(int id, String title, String author, String isbn, int categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.categoryId = categoryId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
