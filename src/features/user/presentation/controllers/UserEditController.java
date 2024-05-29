package features.user.presentation.controllers;

import features.user.datasources.*;
import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;
import features.user.presentation.views.IUserEditView;
import infraestructure.IPresentationManager;
import infraestructure.PresentationManager;

public class UserEditController implements IUserEditController {
    private final IUserRepository _userRepository;
    private final IUserNotifier _userNotifier;
    private final IPresentationManager _presentationManager;

    public UserEditController(IPresentationManager presentationManager, IUserRepository userRepository, IUserNotifier userNotifier) {
        _userRepository = userRepository;
        _userNotifier = userNotifier;
        _presentationManager = presentationManager;
    }

    public void closeWindow() {
        _userNotifier.notifyUserChanged();
        _presentationManager.closeWindow(IUserEditView.class);
    }

    public void createUser(String login, String password, EProfileEntity profile) {
        UserEntity newUserEntity = new UserEntity(login, password, profile);
        _userRepository.addUser(newUserEntity);
    }

    public void editUser(UserEntity userEntity, String login, EProfileEntity profile) {
        userEntity.edit(login, profile);
        _userRepository.editUser(userEntity);
    }
}
