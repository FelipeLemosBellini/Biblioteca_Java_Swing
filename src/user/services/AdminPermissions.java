package user.services;

import user.entities.AdministratorEntity;
import user.entities.UserEntity;

public class AdminPermissions {
    public static boolean verifyAdminUser(UserEntity userEntity) {
        return userEntity instanceof AdministratorEntity;
    }
}
