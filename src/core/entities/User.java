package core.entities;

import core.enums.ECategory;
import core.enums.EProfile;

public class User {
    private int id;

    private String login;

    private String password;

    private EProfile profile;

    public User(int id, String login, EProfile profile) {
        this.login = login;
        this.password = null;
        this.profile = profile;
        this.id = id;
    }
    
    public User(String login, String password, EProfile profile) {
        this.login = login;
        this.password = password;
        this.profile = profile;
    }

    public User(int id, String login, String password, EProfile profile) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.profile = profile;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public EProfile getProfile() {
        return profile;
    }

    public boolean verifyPassword(String passwordToTest) {
        if(passwordToTest == null || passwordToTest.isEmpty())
            return false;
        
        return this.password.equals(passwordToTest);
    }

    public boolean changePassword(String oldPassword, String newPassword, String confirmNewPassword) {
        if (!verifyPassword(oldPassword) || !newPassword.equals(confirmNewPassword))
            return false;
        
        this.password = newPassword;
        return true;
    }

    public void edit(String login, EProfile profile) {
        this.login = login;
        this.profile = profile;
    }
}
