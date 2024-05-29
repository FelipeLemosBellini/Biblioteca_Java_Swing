package features.books.presentation.controllers;

import features.books.entities.BookEntity;

import java.util.Date;

public interface IBookLoanController {
    void closeWindow();
    boolean borrowBook(BookEntity bookEntity, Date dateOfReturning);
    void returnBook(BookEntity bookEntity);
}
