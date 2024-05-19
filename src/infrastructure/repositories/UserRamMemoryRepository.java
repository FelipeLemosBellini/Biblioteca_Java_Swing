package infrastructure.repositories;

import core.entities.Book;
import core.entities.User;
import core.enums.EProfile;
import infrastructure.interfaces.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRamMemoryRepository implements IUserRepository {
    private int sequence = 0;
    private List<User> listOfUsers = new ArrayList<>();
    
    public UserRamMemoryRepository() {
        SeedList();
    }
    
    private void SeedList(){
        createUser(new User("admin", "admin", EProfile.admin));
        createUser(new User("employee", "employee", EProfile.employee));
    }
    
    @Override
    public void createUser(User user) {
        user.setId(getSequence());
        listOfUsers.add(user);
    }

    @Override
    public void deleteUser(User user) {
        listOfUsers.removeIf(u -> Objects.equals(u.getId(), user.getId()));
    }

    @Override
    public User getUser(int id) {
        for (User user : listOfUsers) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUser(String login) {
        for (User user : listOfUsers) {
            if (Objects.equals(user.getLogin(), login)) {
                return user;
            }
        }
        return null;
    }
    
    private int getSequence(){
        this.sequence++;
        return this.sequence;
    }
}
