package core.interfaces;

import core.entities.User;

public interface ICurrentUser {
    void setCurrentUser(User user) throws Exception;
    User getCurrentUser();
}
