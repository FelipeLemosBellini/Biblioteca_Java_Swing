package features.user.datasources;

import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;

import java.util.List;

public class UserRepositoryImpl implements IUserRepository {
    @Override
    public void createUser(UserEntity userEntity) {
        
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

    }

    @Override
    public UserEntity getUser(int id) {
        return null;
    }

    @Override
    public List<UserEntity> searchUser(String searchString) {
        return List.of();
    }
}
