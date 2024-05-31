package features.menu;

import features.books.presentation.views.IBooksView;
import features.login.presentation.ILoginView;
import features.messageInformer.IInformationView;
import features.permissions.AdminPermissions;
import features.session.ICurrentUser;
import features.user.presentation.views.IUsersView;
import infraestructure.IPresentationManager;

public class MenuController implements IMenuController {
    private final IPresentationManager _presentationManager;
    private final ICurrentUser _currentUser;

    public MenuController(
            IPresentationManager presentationManager,
            ICurrentUser currentUser
    ) {
        _presentationManager = presentationManager;
        _currentUser = currentUser;
    }

    public void openUsersWindow() {
        if (AdminPermissions.verifyAdminUser(_currentUser.getCurrentUser()))
            _presentationManager.openWindow(IUsersView.class);
        else
            _presentationManager.openWindow(IInformationView.class, "Usuário sem permissões o suficiente");
    }

    public void openBooksWindow() {
        _presentationManager.openWindow(IBooksView.class);
    }

    @Override
    public void logout() {
        _presentationManager.closeWindow(IMenuView.class);
        _currentUser.cleanUser();
        _presentationManager.openWindow(ILoginView.class);
    }
}
