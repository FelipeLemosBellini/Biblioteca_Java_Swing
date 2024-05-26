package core.entities;

public class Administrator extends User {
    public Administrator(User user) {
        super(user.getId(), user.getLogin(), user.getProfile());
    }
}
