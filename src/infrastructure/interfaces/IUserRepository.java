package infrastructure.interfaces;

import core.entities.User;

public interface IUserRepository {
    void createUser(User user);

    void deleteUser(User user);

    User getUser(int id);
    
    User getUser(String login);
}
