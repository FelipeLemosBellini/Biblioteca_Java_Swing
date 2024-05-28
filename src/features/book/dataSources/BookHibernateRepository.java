package features.book.dataSources;

import features.book.entities.BookEntity;
import infraestructure.IPersistentDataRepository;

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
}
