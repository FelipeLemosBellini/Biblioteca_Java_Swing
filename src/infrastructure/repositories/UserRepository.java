package infrastructure.repositories;

import core.entities.User;
import infrastructure.interfaces.IUserRepository;

public class UserRepository implements IUserRepository {
    @Override
    public boolean verifyUserLogin(String email, String password) {
        return false;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void deleteUser(User user) {
//
    }

    @Override
    public void getUser() {

    }
}
