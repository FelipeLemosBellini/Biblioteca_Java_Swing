package core.entities;

import core.enums.EProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends User {

    public Employee(String login, String password, EProfile profile) {
        super(login, password, profile);
    }
}
