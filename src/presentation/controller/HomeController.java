package presentation.controller;

import core.entities.Administrator;
import core.entities.User;
import presentation.PresentationManager;

public class HomeController {
    private final PresentationManager _presentationManager;

    public HomeController(
            PresentationManager presentationManager
    ) {
        _presentationManager = presentationManager;
    }

    public void openUserManager() {
        _presentationManager.startUserManagement();
    }

    public void openLibraryManager() {
        _presentationManager.startLibrary();
    }
}
