package user.datasources;

import user.entities.EProfileEntity;
import user.entities.UserEntity;

import java.util.List;

public interface IUserRepository {
    void createUser(UserEntity userEntity);

    boolean editUser(UserEntity userEntity, String login, EProfileEntity profile);

    boolean changePassword(UserEntity userEntity, String oldPassword, String newPassword, String confirmNewPassword);

    void removeUser(UserEntity userEntity);

    UserEntity getUser(int id);
    
    UserEntity getUser(String login);

    List<UserEntity> searchUser(String searchString);
    
}
