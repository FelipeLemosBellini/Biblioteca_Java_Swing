package features.books.presentation.controllers;

import features.books.entities.BookEntity;
import features.books.entities.ECategoryEntity;

public interface IBookEditController {
    void closeWindow();
    void createBook(String name, String author, ECategoryEntity category, String isbn);
    void editBook(BookEntity bookEntity, String name, String author, ECategoryEntity category, String isbn);
}
