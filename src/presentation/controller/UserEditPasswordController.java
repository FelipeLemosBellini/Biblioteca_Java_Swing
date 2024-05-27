package presentation.controller;

import core.entities.User;
import core.enums.EProfile;
import infrastructure.interfaces.IUserRepository;
import presentation.PresentationManager;
import presentation.contracts.IUserRepositoryListener;
import presentation.model.UserRepositoryListener;

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

    public boolean changePassword(User user, String oldPassword, String newPassword, String confirmNewPassword) {
        return _userRepository.changePassword(user, oldPassword, newPassword, confirmNewPassword);
    }
}
