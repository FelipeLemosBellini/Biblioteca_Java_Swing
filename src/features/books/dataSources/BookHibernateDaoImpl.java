package features.books.dataSources;

import features.books.entities.BookEntity;
import features.books.entities.ECategoryEntity;
import infraestructure.IPersistentDataRepository;

import java.util.Date;
import java.util.List;

public class BookHibernateDaoImpl implements IBookDao {

    IPersistentDataRepository _persistentDataRepository;

    public BookHibernateDaoImpl(IPersistentDataRepository persistentDataRepository) {
        _persistentDataRepository = persistentDataRepository;
    }

    @Override
    public void createBook(BookEntity bookEntity) {
        _persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
            session.persist(bookEntity);
        });
    }

    @Override
    public void deleteBook(BookEntity bookEntity) {
        _persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
            session.remove(bookEntity);
        });
    }

    @Override
    public List<BookEntity> searchBooks(String search) {
        List<BookEntity> bookEntityList = null;

        try {
            bookEntityList = _persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
                if (search == null || search.trim().isEmpty()) {
                    return session.createSelectionQuery("from BookEntity", BookEntity.class).getResultList();
                } else {
                    Long id = null;
                    try {
                        id = Long.parseLong(search);
                    } catch (NumberFormatException e) {
                    }

                    ECategoryEntity category = ECategoryEntity.action.getECategory(search);
                    String query = "from BookEntity where name like :search " +
                            "or author like :search " +
                            "or ISBN like :search" +
                            (category != null ? " or category = :category" : "") +
                            (id != null ? " or id = :id" : "");

                    var queryBuilder = session.createSelectionQuery(query, BookEntity.class)
                            .setParameter("search", "%" + search + "%");

                    if (category != null) {
                        queryBuilder.setParameter("category", category);
                    }

                    if (id != null) {
                        queryBuilder.setParameter("id", id);
                    }

                    return queryBuilder.getResultList();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookEntityList;
    }

    @Override
    public void updateToBorrow(BookEntity bookEntity, Date dateOfReturning) {

    }

    @Override
    public void updateToUnborrowed(BookEntity bookEntity) {

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

    @Override
    public void updateBook(BookEntity bookEntity) {
        
    }
}
