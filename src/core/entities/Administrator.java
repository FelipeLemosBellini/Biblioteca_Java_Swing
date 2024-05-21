package core.entities;

import core.enums.EProfile;

public class Administrator extends User {
    public Administrator(User user) {
        super(user.getId(), user.getLogin(), user.getProfile());
    }
}
