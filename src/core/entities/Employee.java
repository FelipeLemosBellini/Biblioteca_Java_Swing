package core.entities;

import core.enums.EProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends User {
    public Employee(User user) {
        super(user.getId(), user.getLogin(), user.getProfile());
    }
}
