package infrastructure.repositories;

import core.entities.User;
import infrastructure.interfaces.IPersistentDataRepository;
import infrastructure.interfaces.IUserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserHibernateRepository implements IUserRepository {

    IPersistentDataRepository persistentDataRepository;

    UserHibernateRepository(IPersistentDataRepository persistentDataRepository) {
        this.persistentDataRepository = persistentDataRepository;
    }

    @Override
    public void createUser(User user) {
        persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
            session.persist(user);
        });
    }

    @Override
    public void removeUser(User user) {
        persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
            session.remove(user);
        });
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public User getUser(String loginSearch) {
        return  new User();
//        return persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
//            return new session.createSelectionQuery("from User where login = " + loginSearch, User.class)
//                    .getResultList();
//        });
    }

    @Override
    public List<User> searchUser(String searchString) {
        List<User> users = new ArrayList<>();
        return  users;
//        users = persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
//            return session.createSelectionQuery("from User where login = " + searchString, User.class)
//                    .getResultList();
//        });
//        return  users;
    }
}
