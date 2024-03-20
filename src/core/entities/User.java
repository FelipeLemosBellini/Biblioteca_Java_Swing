package core.entities;

import core.enums.EProfile;

public class User {
    private final String login;

    private final String password;

    private final EProfile profile;

    public User(String login, String password, EProfile profile) {
        this.login = login;
        this.password = password;
        this.profile = profile;
    }

    String getLogin() {
        return login;
    }

    EProfile getProfile() {
        return profile;
    }

    boolean verifyPassword(String passwordToTest) {
        return this.password.equals(passwordToTest);
    }
}
