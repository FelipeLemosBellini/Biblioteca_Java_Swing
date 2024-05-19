package core.services;

import core.entities.Administrator;
import core.entities.User;

public class AdminPermissions {
    public static boolean verifyAdminUser(User user) {
        return user instanceof Administrator;
    }
}
