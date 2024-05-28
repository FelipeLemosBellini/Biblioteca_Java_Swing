package features.menu;

import infraestructure.PresentationManager;

public class MenuController {
    private final PresentationManager _presentationManager;

    public MenuController(
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
