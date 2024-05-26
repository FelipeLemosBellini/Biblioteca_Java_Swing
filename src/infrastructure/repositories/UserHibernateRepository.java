package infrastructure.repositories;

import core.entities.User;
import core.enums.EProfile;
import infrastructure.interfaces.IPersistentDataRepository;
import infrastructure.interfaces.IUserRepository;
import presentation.PresentationManager;

import java.util.ArrayList;
import java.util.List;

public class UserHibernateRepository implements IUserRepository {

    IPersistentDataRepository persistentDataRepository;

    public UserHibernateRepository(IPersistentDataRepository persistentDataRepository) {
        this.persistentDataRepository = persistentDataRepository;
        SeedList();
    }

    private void SeedList() {
        createUser(new User("admin", "admin", EProfile.admin));
        createUser(new User("employee", "employee", EProfile.employee));
    }

    @Override
    public void createUser(User user) {
        try {
            persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
                session.persist(user);
            });
            System.out.println("Usuário criado com sucesso: " + user);
        } catch (Exception e) {
            System.err.println("Erro ao criar usuário: " + user);
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(User user) {
        persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
            session.remove(user);
        });
    }

    @Override
    public User getUser(int id) {
        User user = null;
        try {
            user = persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from User where id = :id", User.class)
                        .setParameter("id", id)
                        .uniqueResultOptional()
                        .orElse(null);
            });
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuário com id " + id);
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUser(String loginSearch) {
        User user = null;
        try {
            user = persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createQuery("from User where login = :login", User.class)
                        .setParameter("login", loginSearch)
                        .uniqueResultOptional()
                        .orElse(null);
            });
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuário com login " + loginSearch);
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> searchUser(String searchString) {
        List<User> users = null;
        try {
            users = persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createQuery("from User", User.class).getResultList();
            });
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os usuários");
            e.printStackTrace();
        }
        return users;
    }
}
