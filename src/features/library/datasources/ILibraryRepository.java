package features.library.datasources;

import features.book.entities.BookEntity;

import java.util.List;

public interface ILibraryRepository {
    List<BookEntity> searchBooks(String search);
    BookEntity getBook(int id);
}
