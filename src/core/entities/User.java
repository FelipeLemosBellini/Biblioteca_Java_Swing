package core.entities;

import core.enums.EProfile;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "login")
    private final String login;
    @Column(name = "password")
    private final String password;
    @Column(name = "profile")
    private final EProfile profile;

    public User(String login, String password, EProfile profile) {
        this.login = login;
        this.password = password;
        this.profile = profile;
    }

    protected int getId() {
        return id;
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
