package features.user.entities;

public class AdministratorEntity extends UserEntity {
    public AdministratorEntity(UserEntity userEntity) {
        super(userEntity.getId(), userEntity.getLogin(), userEntity.getProfile());
    }
}
