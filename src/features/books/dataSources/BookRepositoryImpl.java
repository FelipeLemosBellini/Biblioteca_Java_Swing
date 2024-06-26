package features.books.dataSources;

import features.books.entities.BookEntity;

import java.util.List;

public class BookRepositoryImpl implements IBookRepository {
    private final IBookDao _bookDao;

    public BookRepositoryImpl(IBookDao bookDao) {
        _bookDao = bookDao;
    }

    @Override
    public void addBook(BookEntity bookEntity) {
        _bookDao.createBook(bookEntity);
    }

    @Override
    public void removeBook(BookEntity bookEntity) {
        _bookDao.deleteBook(bookEntity);
    }

    @Override
    public List<BookEntity> searchBooks(String search) {
        return _bookDao.searchBooks(search);
    }

    @Override
    public BookEntity getBook(int id) {
        return _bookDao.readBook(id);
    }

    @Override
    public void editBook(BookEntity bookEntity) {
        _bookDao.updateBook(bookEntity);
    }
}
