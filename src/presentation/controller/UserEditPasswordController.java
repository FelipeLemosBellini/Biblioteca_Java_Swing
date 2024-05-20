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

    public UserEditPasswordController(PresentationManager presentationManager , UserRepositoryListener userRepositoryListener) {
        _userRepositoryListener = userRepositoryListener;
        _presentationManager = presentationManager;
    }
    
    public void closeWindow(){
        _userRepositoryListener.notifyDataChanged();
        _presentationManager.closeWindow("UserEditPassword");
    }
}
