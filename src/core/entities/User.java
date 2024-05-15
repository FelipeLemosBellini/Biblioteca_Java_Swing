package core.entities;

import core.enums.EProfile;

public abstract class User {
    private final String login;

    private final String password;

    private final EProfile profile;

    public User(String login, String password, EProfile profile) {
        this.login = login;
        this.password = password;
        this.profile = profile;
    }

    protected String getLogin() {
        return login;
    }

    protected EProfile getProfile() {
        return profile;
    }

    protected boolean verifyPassword(String passwordToTest) {
        return this.password.equals(passwordToTest);
    }
}
