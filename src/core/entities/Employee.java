package core.entities;

import core.enums.EProfile;

public class Employee extends User {

    public Employee(String login, String password, EProfile profile) {
        super(login, password, profile);
    }

}
