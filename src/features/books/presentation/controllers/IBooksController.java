package features.books.presentation.controllers;

import features.books.dataSources.IBookListener;
import features.books.entities.BookEntity;

import java.util.List;

public interface IBooksController {
    void addListener(IBookListener listener);
    void openBookEdit(BookEntity bookEntity);
    void openBookLending(BookEntity bookEntity);
    void deleteBook(BookEntity bookEntity);
    BookEntity getBook(int id);
    List<BookEntity> getBook(String search);
}
