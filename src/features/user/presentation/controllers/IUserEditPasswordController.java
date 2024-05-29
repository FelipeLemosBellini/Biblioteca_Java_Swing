package features.user.presentation.controllers;

import features.user.entities.UserEntity;

public interface IUserEditPasswordController {
    void closeWindow();
    boolean changePassword(UserEntity userEntity, String newPassword, String confirmNewPassword);
}
