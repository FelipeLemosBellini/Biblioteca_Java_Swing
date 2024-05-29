package features.user.datasources;

import features.user.entities.UserEntity;

import java.util.List;

public interface IUserDao {
    void createUser(UserEntity bookEntity);
    UserEntity readUser(int id);
    void updateUser(UserEntity bookEntity);
    void deleteUser(UserEntity bookEntity);

    List<UserEntity> searchUsers(String search);
}
