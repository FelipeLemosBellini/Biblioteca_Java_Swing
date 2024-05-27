package features.permissions;

import features.user.entities.AdministratorEntity;
import features.user.entities.UserEntity;

public class AdminPermissions {
    public static boolean verifyAdminUser(UserEntity userEntity) {
        return userEntity instanceof AdministratorEntity;
    }
}
