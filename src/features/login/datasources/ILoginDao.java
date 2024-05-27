package features.login.datasources;

import features.user.entities.UserEntity;

public interface ILoginDao {
    boolean verifyUserLogin(String username, String password);

    UserEntity readUser(String login);
}
