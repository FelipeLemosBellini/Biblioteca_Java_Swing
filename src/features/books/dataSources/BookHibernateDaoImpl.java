package features.books.dataSources;

import features.books.entities.BookEntity;
import features.books.entities.ECategoryEntity;
import infraestructure.IPersistenceObject;

import java.util.List;

public class BookHibernateDaoImpl implements IBookDao {
    IPersistenceObject _persistentDataRepository;

    public BookHibernateDaoImpl(IPersistenceObject persistentDataRepository) {
        _persistentDataRepository = persistentDataRepository;
    }

    @Override
    public void createBook(BookEntity bookEntity) {
        _persistentDataRepository.getDatabaseSession().inTransaction(session -> {
            session.persist(bookEntity);
        });
    }

    @Override
    public void deleteBook(BookEntity bookEntity) {
        _persistentDataRepository.getDatabaseSession().inTransaction(session -> {
            session.remove(bookEntity);
        });
    }

    @Override
    public List<BookEntity> searchBooks(String search) {
        List<BookEntity> bookEntityList = null;

        try {
            bookEntityList = _persistentDataRepository.getDatabaseSession().fromTransaction(session -> {
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
    public BookEntity readBook(int id) {
        BookEntity bookEntity = null;

        try {
            bookEntity = _persistentDataRepository.getDatabaseSession().fromTransaction(session -> {
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
        _persistentDataRepository.getDatabaseSession().inTransaction(session -> {
            session.merge(bookEntity);
        });
    }
}
