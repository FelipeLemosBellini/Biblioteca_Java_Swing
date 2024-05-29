package features.user.presentation.controllers;

import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;

public interface IUserEditController {
    void closeWindow();
    void createUser(String login, String password, EProfileEntity profile);
    void editUser(UserEntity userEntity, String login, EProfileEntity profile);
}
