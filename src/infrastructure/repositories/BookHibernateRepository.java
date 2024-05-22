package infrastructure.repositories;

import core.entities.Book;
import infrastructure.interfaces.IBookRepository;
import infrastructure.interfaces.IPersistentDataRepository;

import java.util.List;

public class BookHibernateRepository implements IBookRepository {

    IPersistentDataRepository persistentDataRepository;

    BookHibernateRepository(IPersistentDataRepository persistentDataRepository) {
        this.persistentDataRepository = persistentDataRepository;
    }

    @Override
    public void addBook(Book book) {
        persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
            session.persist(book);
        });
    }

    @Override
    public void removeBook(Book book) {
        persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
            session.remove(book);
        });
    }

    @Override
    public Book getBook(int id) {
        return new Book();
//        persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
////            session.byId<Book>(id);
//        });
    }

    @Override
    public List<Book> searchBook(String searchString) {
        return null;
    }
}
