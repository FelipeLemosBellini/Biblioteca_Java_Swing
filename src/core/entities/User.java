package core.entities;

import core.enums.ECategory;
import core.enums.EProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "User")
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setProfile(EProfile profile) {
        this.profile = profile;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
