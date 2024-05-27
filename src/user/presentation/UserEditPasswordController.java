package user.presentation;

import user.datasources.UserRepositoryListener;
import user.datasources.IUserRepository;
import user.entities.UserEntity;
import utils.PresentationManager;

public class UserEditPasswordController {
    private final UserRepositoryListener _userRepositoryListener;
    private final PresentationManager _presentationManager;
    private final IUserRepository _userRepository;

    public UserEditPasswordController(PresentationManager presentationManager, UserRepositoryListener userRepositoryListener, IUserRepository userRepository) {
        _userRepositoryListener = userRepositoryListener;
        _presentationManager = presentationManager;
        _userRepository = userRepository;
    }

    public void closeWindow() {
        _userRepositoryListener.notifyDataChanged();
        _presentationManager.closeWindow("UserEditPassword");
    }

    public boolean changePassword(UserEntity userEntity, String oldPassword, String newPassword, String confirmNewPassword) {
        return _userRepository.changePassword(userEntity, oldPassword, newPassword, confirmNewPassword);
    }
}
