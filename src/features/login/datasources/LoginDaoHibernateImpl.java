package features.login.datasources;

import infraestructure.IPersistenceObject;
import features.user.entities.UserEntity;

import java.util.Optional;

public class LoginDaoHibernateImpl implements ILoginDao {
    IPersistenceObject _persistentDataRepository;

    public LoginDaoHibernateImpl(IPersistenceObject persistentDataRepository) {
        _persistentDataRepository = persistentDataRepository;
    }

    @Override
    public boolean verifyUserLogin(String login, String password) {
        boolean isValidUser = false;

        try {
            Optional<UserEntity> userEntity = _persistentDataRepository.getDatabaseSession().fromTransaction(session -> {
                return session.createQuery("from UserEntity where login = :login and password = :password", UserEntity.class)
                        .setParameter("login", login)
                        .setParameter("password", password)
                        .uniqueResultOptional();
            });

            isValidUser = userEntity.isPresent();

        } catch (Exception e) {
            System.err.println("Error verifying user login: " + e.getMessage());
            e.printStackTrace();
        }

        return isValidUser;
    }

    @Override
    public UserEntity readUser(String login) {
        UserEntity userEntity = null;
        try {
            userEntity = _persistentDataRepository.getDatabaseSession().fromTransaction(session -> {
                return session.createQuery("from UserEntity where login = :login", UserEntity.class)
                        .setParameter("login", login)
                        .uniqueResultOptional()
                        .orElse(null);
            });
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuário com login " + login);
            e.printStackTrace();
        }
        return userEntity;
    }
}
