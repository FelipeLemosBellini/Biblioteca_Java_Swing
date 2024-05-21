package infrastructure.interfaces;

import core.entities.User;

import java.util.List;

public interface IUserRepository {
    void createUser(User user);

    void removeUser(User user);

    User getUser(int id);
    
    User getUser(String login);

    List<User> searchUser(String searchString);
    
}
