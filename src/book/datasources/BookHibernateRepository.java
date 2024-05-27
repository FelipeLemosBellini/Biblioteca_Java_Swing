package book.datasources;

import book.entities.BookEntity;
import hibernate.IPersistentDataRepository;

import java.util.Date;
import java.util.List;

public class BookHibernateRepository implements IBookRepository {

    IPersistentDataRepository persistentDataRepository;

    public BookHibernateRepository(IPersistentDataRepository persistentDataRepository) {
        this.persistentDataRepository = persistentDataRepository;
    }

    @Override
    public void addBook(BookEntity bookEntity) {
        persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
            session.persist(bookEntity);
        });
    }

    @Override
    public void removeBook(BookEntity bookEntity) {
        persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
            session.remove(bookEntity);
        });
    }

    @Override
    public BookEntity getBook(int id) {
        BookEntity bookEntity = null;
        try {
            bookEntity = persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from BookEntity where id = :id", BookEntity.class)
                        .setParameter("id", id)
                        .getSingleResultOrNull();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookEntity;
    }

    @Override
    public List<BookEntity> searchBook(String searchString) {
        List<BookEntity> bookEntityList = null;
        try {
            bookEntityList = persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from BookEntity where name = :name", BookEntity.class)
                        .setParameter("name", searchString)
                        .getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookEntityList;
    }

    @Override
    public boolean borrow(BookEntity bookEntity, Date dateOfReturning) {
        return false;
    }

    @Override
    public void returnTheBook(BookEntity bookEntity) {

    }
}
