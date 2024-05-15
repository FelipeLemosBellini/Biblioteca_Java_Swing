package infrastructure.interfaces;

import core.entities.User;

public interface IUserRepository {
    boolean verifyUserLogin(String email, String password);

    void createUser(User user);

    void deleteUser(User user);

    void getUser();
}
