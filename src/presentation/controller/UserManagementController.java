package presentation.controller;

import core.entities.User;
import core.services.AdminPermissions;
import presentation.PresentationManager;

public class UserManagementController {
    private final PresentationManager _presentationManager;

    public UserManagementController(
            PresentationManager presentationManager
    ) {
        _presentationManager = presentationManager;
        
    }

    public void closeWindow(){
        _presentationManager.closeWindow("UserManagement");
    }

    public void addListener(){
//        _bookRepositoryListener.subscribe(listener);
    }
}
