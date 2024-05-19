package core.entities;

import core.enums.EProfile;

public class Employee extends User {
    public Employee(User user) {
        super(user.getId(), user.getLogin(), user.getProfile());
    }
}
