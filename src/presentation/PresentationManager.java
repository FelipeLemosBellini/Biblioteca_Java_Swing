package presentation;

import core.entities.User;
import core.interfaces.ICurrentUser;
import core.services.AdminPermissions;
import infrastructure.interfaces.IBookRepository;
import infrastructure.interfaces.IUserRepository;
import infrastructure.repositories.*;

import presentation.controller.*;
import presentation.model.BookRepositoryListener;
import presentation.model.UserRepositoryListener;
import presentation.view.*;
import core.entities.Book;

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

    public void startUserEdit(User user) {
        createWindow("UserEdit", () -> {
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getUserEditView();
        });
    }

    public void startUserPasswordEdit(User user) {
        createWindow("UserEditPassword", () -> {
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getUserEditPasswordView();
        });
    }

    public void startBookEdit(Book book) {
        createWindow("BookEdit", () -> {
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getBookEditView(book);
        });
    }

    public void startBookLending(Book book) {
        createWindow("BookLending", () -> {
            var serviceLocator = ServiceLocator.getInstance();
            return serviceLocator.getBookLendingView(book);
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