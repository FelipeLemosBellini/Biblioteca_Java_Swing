package features.books.dataSources;

import features.books.entities.BookEntity;

import java.util.Date;
import java.util.List;

public interface IBookRepository {
    void addBook(BookEntity bookEntity);
    BookEntity getBook(int id);
    void editBook(BookEntity bookEntity);
    void removeBook(BookEntity bookEntity);
    
    List<BookEntity> searchBooks(String search);
}
 