package infraestructure;

import construtionSet.dependencyInjection.implementations.ServiceLocatorImpl;
import features.books.presentation.BookEditView;
import features.books.presentation.BookLoanView;
import features.books.presentation.BooksView;
import features.login.presentation.LoginView;
import features.menu.MenuView;
import features.messageInformer.InformationView;
import features.user.entities.UserEntity;
import features.session.ICurrentUser;
import features.permissions.AdminPermissions;

import features.books.entities.BookEntity;
import features.user.presentation.UserEditPasswordView;
import features.user.presentation.UserEditView;
import features.user.presentation.UserManagementView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class PresentationManager {
    private ICurrentUser _currentUser;

    private final Map<String, JFrame> openWindows;

    public PresentationManager(ICurrentUser currentUser) {
        _currentUser = currentUser;
        openWindows = new HashMap<>();
    }

    public void startLogin() {  
        createWindow("Login", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (LoginView)serviceLocator.getService(LoginView.class);
        });
    }

    public void startHome() {
        createWindow("Home", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (MenuView)serviceLocator.getService(MenuView.class);
        });
    }

    public void startLibrary() {
        createWindow("Library", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (BooksView)serviceLocator.getService(BooksView.class);
        });
    }

    public void startUserManagement() {
        if (AdminPermissions.verifyAdminUser(_currentUser.getCurrentUser())) {
            createWindow("UserManagement", () -> {
                var serviceLocator = ServiceLocatorImpl.getInstance();
                return (UserManagementView)serviceLocator.getService(UserManagementView.class);
            });
        } else
            startInformationWindow("Usuário sem permissão de acesso");
    }

    public void startUserEdit(UserEntity userEntity) {
        createWindow("UserEdit", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (UserEditView)serviceLocator.getService(UserEditView.class);
        });
    }

    public void startUserPasswordEdit(UserEntity userEntity) {
        createWindow("UserEditPassword", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (UserEditPasswordView)serviceLocator.getService(UserEditPasswordView.class, userEntity);
        });
    }

    public void startBookEdit(BookEntity bookEntity) {
        createWindow("BookEdit", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (BookEditView)serviceLocator.getService(BookEditView.class, bookEntity);
        });
    }

    public void startBookLending(BookEntity bookEntity) {
        createWindow("BookLending", () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (BookLoanView)serviceLocator.getService(BookLoanView.class, bookEntity);
        });
    }

    public void startInformationWindow(String message) {
        createWindow("Information_" + message.replace(" ", "_"), () -> {
            var serviceLocator = ServiceLocatorImpl.getInstance();
            return (InformationView)serviceLocator.getService(InformationView.class, message);
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