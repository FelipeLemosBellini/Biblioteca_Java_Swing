package features.user.datasources;

import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;
import infraestructure.IPersistenceObject;

import java.util.List;

public class UserHibernateDAOImpl implements IUserDao {
    IPersistenceObject _persistentDataRepository;

    public UserHibernateDAOImpl(IPersistenceObject persistentDataRepository) {
        this._persistentDataRepository = persistentDataRepository;
    }
    
    @Override
    public void createUser(UserEntity userEntity) {
        try {
            _persistentDataRepository.getDatabaseSession().inTransaction(session -> {
                session.persist(userEntity);
            });
            System.out.println("Usuário criado com sucesso: " + userEntity);
        } catch (Exception e) {
            System.err.println("Erro ao criar usuário: " + userEntity);
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        _persistentDataRepository.getDatabaseSession().inTransaction(session -> {
            session.merge(userEntity);
        });
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        _persistentDataRepository.getDatabaseSession().inTransaction(session -> {
            session.remove(userEntity);
        });
    }

    @Override
    public UserEntity readUser(int id) {
        UserEntity userEntity = null;
        try {
            userEntity = _persistentDataRepository.getDatabaseSession().fromTransaction(session -> {
                return session.createSelectionQuery("from UserEntity where id = :id", UserEntity.class)
                        .setParameter("id", id)
                        .uniqueResultOptional()
                        .orElse(null);
            });
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuário com id " + id);
            e.printStackTrace();
        }
        return userEntity;
    }

    @Override
    public UserEntity readUser(String login) {
        UserEntity userEntity = null;
        try {
            userEntity = _persistentDataRepository.getDatabaseSession().fromTransaction(session -> {
                return session.createSelectionQuery("from UserEntity where login = :login", UserEntity.class)
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

    @Override
    public List<UserEntity> searchUsers(String searchString) {
        List<UserEntity> userEntities = null;
        try {
            userEntities = _persistentDataRepository.getDatabaseSession().fromTransaction(session -> {
                if (searchString == null || searchString.trim().isEmpty()) {
                    return session.createSelectionQuery("from UserEntity", UserEntity.class).getResultList();
                } else {
                    int id = 0;
                    try {
                        id = Integer.parseInt(searchString);
                    } catch (NumberFormatException e) {
                    }

                    EProfileEntity profile = EProfileEntity.admin.getEProfile(searchString);

                    String query = "from UserEntity where login like :search " +
                            (profile != null ? "or profile = :profile" : "") +
                            (id != 0 ? "or id = :id" : "");

                    var queryBuilder = session.createSelectionQuery(query, UserEntity.class)
                            .setParameter("search", "%" + searchString + "%");

                    if (profile != null) {
                        queryBuilder.setParameter("profile", profile);
                    }

                    if (id != 0) {
                        queryBuilder.setParameter("id", id);
                    }

                    return queryBuilder.getResultList();
                }
            });
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuários: " + e.getMessage());
            e.printStackTrace();
        }
        return userEntities;
    }
}
