package features.menu;

import features.books.presentation.views.IBooksView;
import features.user.presentation.views.IUsersView;
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
        _presentationManager.openWindow(IUsersView.class);
    }

    public void openLibraryManager() {
        _presentationManager.openWindow(IBooksView.class);
    }
}
