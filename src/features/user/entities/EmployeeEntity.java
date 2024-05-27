package features.user.entities;

public class EmployeeEntity extends UserEntity {
    public EmployeeEntity(UserEntity userEntity) {
        super(userEntity.getId(), userEntity.getLogin(), userEntity.getProfile());
    }
}
