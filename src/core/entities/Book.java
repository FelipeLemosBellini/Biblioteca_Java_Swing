package core.entities;

import core.enums.EBook;

public class Book {
    protected int id;
    protected String name;
    protected String author;
    protected EBook category;
    protected String ISBN;

    public Book(int id, String name, String author, EBook category, String ISBN) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.ISBN = ISBN;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public EBook getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(EBook category) {
        this.category = category;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

}
