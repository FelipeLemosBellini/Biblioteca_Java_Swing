package infrastructure.repositories;

import core.entities.Book;
import core.entities.User;
import infrastructure.interfaces.IBookRepository;
import infrastructure.interfaces.IPersistentDataRepository;

import java.util.Date;
import java.util.List;

public class BookHibernateRepository implements IBookRepository {

    IPersistentDataRepository persistentDataRepository;

    public BookHibernateRepository(IPersistentDataRepository persistentDataRepository) {
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
        Book book = null;
        try {
            book = persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Book where id = :id", Book.class)
                        .setParameter("id", id)
                        .getSingleResultOrNull();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> searchBook(String searchString) {
        List<Book> bookList = null;
        try {
            bookList = persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Book where name = :name", Book.class)
                        .setParameter("name", searchString)
                        .getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public boolean borrow(Book book, Date dateOfReturning) {
        return false;
    }

    @Override
    public void returnTheBook(Book book) {

    }
}
