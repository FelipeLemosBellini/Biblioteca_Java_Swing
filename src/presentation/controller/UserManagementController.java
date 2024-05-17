package presentation.controller;

import infrastructure.interfaces.IBookRepository;
import presentation.PresentationManager;
import presentation.contracts.IBookRepositoryListener;
import presentation.model.BookRepositoryListener;

public class UserManagementController {
    private final PresentationManager _presentationManager;

    public UserManagementController(
            PresentationManager presentationManager
    ) {
        _presentationManager = presentationManager;
    }

    public void closeWindow(){
    }

    public void addListener(){
//        _bookRepositoryListener.subscribe(listener);
    }
}
