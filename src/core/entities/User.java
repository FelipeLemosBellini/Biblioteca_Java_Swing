package core.entities;

import core.enums.ECategory;
import core.enums.EProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "profile")
    private EProfile profile;

    public User() {
    }

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
        if (passwordToTest == null || passwordToTest.isEmpty())
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
