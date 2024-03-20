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

    public String getLogin() {
        return login;
    }

    public EProfile getProfile() {
        return profile;
    }

    public boolean verifyPassword(String passwordToTest) {
        return this.password.equals(passwordToTest);
    }
}
