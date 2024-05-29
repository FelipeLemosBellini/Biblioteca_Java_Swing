package features.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "login", unique = true)
    private String login;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "profile")
    private EProfileEntity profile;

    public UserEntity() {
    }

    public UserEntity(int id, String login, EProfileEntity profile) {
        this.login = login;
        this.password = null;
        this.profile = profile;
        this.id = id;
    }

    public UserEntity(String login, String password, EProfileEntity profile) {
        this.login = login;
        this.password = password;
        this.profile = profile;
    }

    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public EProfileEntity getProfile() {
        return profile;
    }
    
    public void edit(String login, EProfileEntity profile){
        this.login = login;
        this.profile = profile;
    }

    public boolean editPassword(String newPassword, String confirmNewPassword){
        if (!newPassword.equals(confirmNewPassword))
            return false;

        this.password = newPassword;

        return true;
    }
    

    public boolean verifyPassword(String passwordToTest) {
        if (passwordToTest == null || passwordToTest.isEmpty())
            return false;

        return this.password.equals(passwordToTest);
    }
}
