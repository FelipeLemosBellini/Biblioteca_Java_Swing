package features.book.datasources;

import features.book.entities.BookEntity;

import java.util.Date;
import java.util.List;

public interface IBookRepository {
    void addBook(BookEntity bookEntity);
    void removeBook(BookEntity bookEntity);
    BookEntity getBook(int id);
    List<BookEntity> searchBook(String searchString);
    boolean borrow(BookEntity bookEntity, Date dateOfReturning);
    void returnTheBook(BookEntity bookEntity);
}
 