package features.book.dataSources;

import features.book.entities.BookEntity;

import java.util.Date;
import java.util.List;

public interface IBookRepository {
    void addBook(BookEntity bookEntity);
    void removeBook(BookEntity bookEntity);
}
 