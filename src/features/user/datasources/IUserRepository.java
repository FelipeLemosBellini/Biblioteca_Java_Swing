package features.user.datasources;

import features.user.entities.UserEntity;

import java.util.List;

public interface IUserRepository {
    void addUser(UserEntity userEntity);
    UserEntity getUser(int id);
    UserEntity getUser(String login);
    void editUser(UserEntity userEntity);
    void removeUser(UserEntity userEntity);

    List<UserEntity> searchUsers(String searchString);
}
