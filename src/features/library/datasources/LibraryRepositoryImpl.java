package features.library.datasources;

import features.book.entities.BookEntity;
import features.login.datasources.ILoginDao;

import java.util.List;

public class LibraryRepositoryImpl implements ILibraryRepository {
    private ILibraryDao _libraryDao;

    public LibraryRepositoryImpl(ILibraryDao libraryDao) {
        _libraryDao = libraryDao;
    }

    @Override
    public List<BookEntity> searchBooks(String search) {
        return _libraryDao.searchBooks(search);
    }

    @Override
    public BookEntity getBook(int id) {
        return _libraryDao.readBook(id);
    }
}
