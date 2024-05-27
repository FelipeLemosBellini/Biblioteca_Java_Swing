package infrastructure.interfaces;

import core.entities.User;
import core.enums.EProfile;

import java.util.List;

public interface IUserRepository {
    void createUser(User user);

    boolean editUser(User user,String login, EProfile profile);

    boolean changePassword(User user, String oldPassword, String newPassword, String confirmNewPassword);

    void removeUser(User user);

    User getUser(int id);
    
    User getUser(String login);

    List<User> searchUser(String searchString);
    
}
