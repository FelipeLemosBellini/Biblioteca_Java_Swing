package infraestructure;

import dependencyInjection.ServiceLocator;
import features.user.entities.UserEntity;
import features.currentUser.ICurrentUser;
import features.permissions.AdminPermissions;

import features.book.entities.BookEntity;

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
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getLoginView();
        });
    }

    public void startHome() {
        createWindow("Home", () -> {
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getHomeView();
        });
    }

    public void startLibrary() {
        createWindow("Library", () -> {
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getLibraryView();
        });
    }

    public void startUserManagement() {
        if (AdminPermissions.verifyAdminUser(_currentUser.getCurrentUser())) {
            createWindow("UserManagement", () -> {
                var serviceLocator = ServiceLocator.getInstance();
                return serviceLocator.getUserManagementView();
            });
        } else
            startInformationWindow("Usuário sem permissão de acesso");
    }

    public void startUserEdit(UserEntity userEntity) {
        createWindow("UserEdit", () -> {
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getUserEditView();
        });
    }

    public void startUserPasswordEdit(UserEntity userEntity) {
        createWindow("UserEditPassword", () -> {
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getUserEditPasswordView();
        });
    }

    public void startBookEdit(BookEntity bookEntity) {
        createWindow("BookEdit", () -> {
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getBookEditView(bookEntity);
        });
    }

    public void startBookLending(BookEntity bookEntity) {
        createWindow("BookLending", () -> {
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getBookLendingView(bookEntity);
        });
    }

    public void startInformationWindow(String message) {
        createWindow("Information_" + message.replace(" ", "_"), () -> {
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getInformationView(message);
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