package features.library.datasources;

import features.book.entities.BookEntity;
import infraestructure.IPersistentDataRepository;

import java.util.List;

public class LibraryDaoHibernateImpl implements ILibraryDao {
    IPersistentDataRepository _persistentDataRepository;

    public LibraryDaoHibernateImpl(IPersistentDataRepository persistentDataRepository) {
        _persistentDataRepository = persistentDataRepository;
    }

    @Override
    public List<BookEntity> searchBooks(String search) {
        List<BookEntity> bookEntityList = null;
        
        try {
            bookEntityList = _persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from BookEntity where name = :name", BookEntity.class)
                        .setParameter("name", search)
                        .getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookEntityList;
    }

    @Override
    public BookEntity readBook(int id) {
        BookEntity bookEntity = null;
        
        try {
            bookEntity = _persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from BookEntity where id = :id", BookEntity.class)
                        .setParameter("id", id)
                        .getSingleResultOrNull();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookEntity;
    }
}
