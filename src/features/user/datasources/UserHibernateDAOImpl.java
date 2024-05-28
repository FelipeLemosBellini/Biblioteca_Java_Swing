package features.user.datasources;

import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;
import infraestructure.IPersistentDataRepository;

import java.util.List;

public class UserHibernateDAOImpl implements IUserRepository {
    IPersistentDataRepository persistentDataRepository;

    public UserHibernateDAOImpl(IPersistentDataRepository persistentDataRepository) {
        this.persistentDataRepository = persistentDataRepository;
        SeedList();
    }

    private void SeedList() {
        createUser(new UserEntity("admin", "admin", EProfileEntity.admin));
        createUser(new UserEntity("employee", "employee", EProfileEntity.employee));
    }

    @Override
    public void createUser(UserEntity userEntity) {
        try {
            persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
                session.persist(userEntity);
            });
            System.out.println("Usuário criado com sucesso: " + userEntity);
        } catch (Exception e) {
            System.err.println("Erro ao criar usuário: " + userEntity);
            e.printStackTrace();
        }
    }

    @Override
    public boolean editUser(UserEntity userEntity, String login, EProfileEntity profile) {
        return false;
    }

    @Override
    public boolean changePassword(UserEntity userEntity, String oldPassword, String newPassword, String confirmNewPassword) {
        return false;
    }

    @Override
    public void removeUser(UserEntity userEntity) {
        persistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
            session.remove(userEntity);
        });
    }

    @Override
    public UserEntity getUser(int id) {
        UserEntity userEntity = null;
        try {
            userEntity = persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
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
    public List<UserEntity> searchUser(String searchString) {
        List<UserEntity> userEntities = null;
        try {
            userEntities = persistentDataRepository.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createQuery("from UserEntity", UserEntity.class).getResultList();
            });
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os usuários");
            e.printStackTrace();
        }
        return userEntities;
    }
}
