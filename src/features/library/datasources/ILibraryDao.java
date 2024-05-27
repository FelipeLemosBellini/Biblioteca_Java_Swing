package features.library.datasources;

import features.book.entities.BookEntity;

import java.util.List;

public interface ILibraryDao {
    List<BookEntity> searchBooks(String search);
    BookEntity readBook(int id);
}
