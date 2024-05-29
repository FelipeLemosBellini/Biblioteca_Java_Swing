package features.menu;

import infraestructure.IPresentationManager;
import infraestructure.PresentationManager;

public class MenuController implements IMenuController {
    private final IPresentationManager _presentationManager;

    public MenuController(
            IPresentationManager presentationManager
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
