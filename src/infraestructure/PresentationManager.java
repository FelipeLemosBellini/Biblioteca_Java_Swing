package infraestructure;

import construtionSet.dependencyInjection.implementations.ServiceLocatorImpl;
import features.books.presentation.views.*;
import features.login.presentation.ILoginView;
import features.menu.IMenuView;
import features.messageInformer.IInformationView;
import features.user.entities.UserEntity;
import features.session.ICurrentUser;
import features.permissions.AdminPermissions;

import features.books.entities.BookEntity;
import features.user.presentation.views.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class PresentationManager implements IPresentationManager {
    private final ICurrentUser _currentUser;

    private final Map<String, JFrame> openWindows;

    public PresentationManager(ICurrentUser currentUser) {
        _currentUser = currentUser;
        openWindows = new HashMap<>();
    }

    public void startLogin() {  
        createWindow("Login", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (JFrame) serviceLocator.getService(ILoginView.class);
        });
    }

    public void startHome() {
        createWindow("Home", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (JFrame) serviceLocator.getService(IMenuView.class);
        });
    }

    public void startLibrary() {
        createWindow("Library", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (JFrame) serviceLocator.getService(IBooksView.class);
        });
    }

    public void startUserManagement() {
        if (AdminPermissions.verifyAdminUser(_currentUser.getCurrentUser())) {
            createWindow("UserManagement", () -> {
                var serviceLocator = ServiceLocatorImpl.getInstance();
                return (JFrame)serviceLocator.getService(IUsersView.class);
            });
        } else
            startInformationWindow("Usuário sem permissão de acesso");
    }

    public void startUserEdit(UserEntity userEntity) {
        createWindow("UserEdit", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (JFrame) serviceLocator.getService(IUserEditView.class, userEntity);
        });
    }

    public void startUserPasswordEdit(UserEntity userEntity) {
        createWindow("UserEditPassword", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (JFrame)serviceLocator.getService(IUserEditPasswordView.class, userEntity);
        });
    }

    public void startBookEdit(BookEntity bookEntity) {
        createWindow("BookEdit", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (JFrame)serviceLocator.getService(IBookEditView.class, bookEntity);
        });
    }

    public void startBookLending(BookEntity bookEntity) {
        createWindow("BookLending", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (JFrame)serviceLocator.getService(IBookLoanView.class, bookEntity);
        });
    }

    public void startInformationWindow(String message) {
        createWindow("Information_" + message.replace(" ", "_"), () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (JFrame)serviceLocator.getService(IInformationView.class, message);
        });
    }

    public void closeWindow(String windowName) {
        JFrame window = openWindows.get(windowName);
        if (window != null) {
            window.dispose();
            openWindows.remove(windowName);
        }
    }

    private void createWindow(String key, WindowCreator creator) {
        if (!openWindows.containsKey(key)) {
            var view = creator.create();
            openWindows.put(key, view);

            view.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    closeWindow(key);
                }
            });
        } else {
            var view = openWindows.get(key);
            view.toFront();
            view.requestFocus();
        }
    }

    @FunctionalInterface
    interface WindowCreator {
        JFrame create();
    }
}