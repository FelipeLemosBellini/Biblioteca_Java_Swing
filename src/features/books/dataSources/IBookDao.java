package features.books.dataSources;

import features.books.entities.BookEntity;

import java.util.Date;
import java.util.List;

public interface IBookDao {
    void createBook(BookEntity bookEntity);
    BookEntity readBook(int id);
    void updateBook(BookEntity bookEntity);
    void deleteBook(BookEntity bookEntity);
    
    List<BookEntity> searchBooks(String search);
    
    boolean updateToBorrow(BookEntity bookEntity, Date dateOfReturning);
    void updateToUnborrowed(BookEntity bookEntity);
}
