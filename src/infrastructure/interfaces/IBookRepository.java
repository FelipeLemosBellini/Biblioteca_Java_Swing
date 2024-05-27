package infrastructure.interfaces;

import core.entities.Book;
import core.enums.ECategory;

import java.util.Date;
import java.util.List;

public interface IBookRepository {
    void addBook(Book book);
    void removeBook(Book book);
    Book getBook(int id);
    List<Book> searchBook(String searchString);
    boolean borrow(Book book, Date dateOfReturning);
    void returnTheBook(Book book);
}
 