package core.entities;

import core.enums.EBook;

public class Book {
    private final String name;
    private final String author;

    private final EBook category;

    private final String ISBN;

    public Book(String name, String author, EBook category, String ISBN) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.ISBN = ISBN;
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

    EBook getCategory() {
        return category;
    }
}
