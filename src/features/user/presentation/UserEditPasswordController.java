package features.user.presentation;

import features.user.datasources.UserObserverImpl;
import features.user.datasources.IUserRepository;
import features.user.entities.UserEntity;
import infraestructure.PresentationManager;

public class UserEditPasswordController {
    private final UserObserverImpl _userObserverImpl;
    private final PresentationManager _presentationManager;
    private final IUserRepository _userRepository;

    public UserEditPasswordController(PresentationManager presentationManager, UserObserverImpl userObserverImpl, IUserRepository userRepository) {
        _userObserverImpl = userObserverImpl;
        _presentationManager = presentationManager;
        _userRepository = userRepository;
    }

    public void closeWindow() {
        _userObserverImpl.notifyDataChanged();
        _presentationManager.closeWindow("UserEditPassword");
    }

    public boolean changePassword(UserEntity userEntity, String oldPassword, String newPassword, String confirmNewPassword) {
        return _userRepository.changePassword(userEntity, oldPassword, newPassword, confirmNewPassword);
    }
}
