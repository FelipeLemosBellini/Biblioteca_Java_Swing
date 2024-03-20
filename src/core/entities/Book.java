package core.entities;

import core.enums.EBook;

public class Book {
    private String name;
    private String author;

    private EBook category;

    private String ISBN;

    Book(String name, String author, EBook category, String ISBN) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.ISBN = ISBN;
    }

    String getName() {
        return name;
    }

    String getAuthor() {
        return author;
    }

    String getISBN() {
        return ISBN;
    }

    EBook getCategory() {
        return category;
    }
}
