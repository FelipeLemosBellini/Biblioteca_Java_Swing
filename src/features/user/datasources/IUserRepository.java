package features.user.datasources;

import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;

import java.util.List;

public interface IUserRepository {
    void createUser(UserEntity userEntity);

    boolean editUser(UserEntity userEntity, String login, EProfileEntity profile);

    boolean changePassword(UserEntity userEntity, String oldPassword, String newPassword, String confirmNewPassword);

    void removeUser(UserEntity userEntity);

    UserEntity getUser(int id);
    
    List<UserEntity> searchUser(String searchString);
    
}