package features.login.datasources;

import features.user.entities.UserEntity;

public interface ILoginRepository {
    boolean verifyUserLogin(String username, String password);

    UserEntity getUser(String login);
}
