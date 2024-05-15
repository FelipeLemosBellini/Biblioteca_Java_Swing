package core.entities;

import core.enums.EProfile;

public class Administrator extends User {
    public Administrator(String login, String password, EProfile profile) {
        super(login, password, profile);
    }
}
