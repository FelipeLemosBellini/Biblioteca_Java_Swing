package features.user.presentation.controllers;

import features.user.datasources.IUserNotifier;
import features.user.datasources.IUserRepository;
import features.user.entities.UserEntity;
import infraestructure.IPresentationManager;
import infraestructure.PresentationManager;

public class UserEditPasswordController implements IUserEditPasswordController {
    private final IUserNotifier _userNotifier;
    private final IPresentationManager _presentationManager;
    private final IUserRepository _userRepository;

    public UserEditPasswordController(IPresentationManager presentationManager, IUserNotifier userNotifier, IUserRepository userRepository) {
        _userNotifier = userNotifier;
        _presentationManager = presentationManager;
        _userRepository = userRepository;
    }

    public void closeWindow() {
        _userNotifier.notifyUserChanged();
        _presentationManager.closeWindow(IUserEditPasswordController.class);
    }

    public boolean changePassword(UserEntity userEntity, String newPassword, String confirmNewPassword) {
        var passWasEdit = userEntity.editPassword(newPassword, confirmNewPassword);
        if (!passWasEdit)
            return false;
        
        _userRepository.editUser(userEntity);
        
        return true;
    }
}
