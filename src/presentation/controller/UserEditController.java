package presentation.controller;

import core.entities.User;
import core.enums.EProfile;

import infrastructure.interfaces.IUserRepository;

import presentation.PresentationManager;
import presentation.contracts.IUserRepositoryListener;
import presentation.model.UserRepositoryListener;

public class UserEditController {
    private final IUserRepository _userRepository;
    private final UserRepositoryListener _userRepositoryListener;
    private final PresentationManager _presentationManager;

    public UserEditController(PresentationManager presentationManager, IUserRepository userRepository, UserRepositoryListener userRepositoryListener) {
        _userRepository = userRepository;
        _userRepositoryListener = userRepositoryListener;
        _presentationManager = presentationManager;
    }

    public void addListener(IUserRepositoryListener listener) {
        _userRepositoryListener.subscribe(listener);
    }

    public void closeWindow() {
        _userRepositoryListener.notifyDataChanged();
        _presentationManager.closeWindow("UserEdit");
    }

    public void createUser(String login, String password, EProfile profile) {
        User newUser = new User(login, password, profile);
        _userRepository.createUser(newUser);
    }

    public void editUser(User user, String login, EProfile profile) {
        _userRepository.editUser(user, login, profile);
    }
}
