package presentation;

import core.entities.Administrator;
import core.entities.User;
import infrastructure.interfaces.IBookRepository;
import infrastructure.interfaces.IUserRepository;
import infrastructure.repositories.BookRamMemoryRepository;
import infrastructure.repositories.UserRamMemoryRepository;
import presentation.controller.*;
import presentation.model.BookRepositoryListener;
import presentation.view.*;
import core.entities.Book;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class PresentationManager {
    private final BookRepositoryListener _bookRepositoryListener;

    private final IBookRepository _bookRepository;
    private final IUserRepository _userRepository;

    private User _currentUser;

    private final Map<String, JFrame> openWindows;  // Use JFrame specifically

    public PresentationManager() {
        _bookRepository = new BookRamMemoryRepository();
        _bookRepositoryListener = new BookRepositoryListener();

        _userRepository = new UserRamMemoryRepository();

        openWindows = new HashMap<>();

        startLogin();
    }

    public void startLogin() {
        createWindow("Login", () -> {
            var controller = new LoginController(this, _userRepository);
            return new LoginView(controller);
        });
    }

    public void setCurrentUser(User user) {
        _currentUser = user;
    }

    public void startHome() {
        createWindow("Home", () -> {
            var controller = new HomeController(this);
            return new HomeView(this, controller);
        });
    }

    public void startLibrary() {
        createWindow("Library", () -> {
            var controller = new LibraryController(this, _bookRepository, _bookRepositoryListener);
            return new LibraryView(controller, this);
        });
    }

    public void startUserManagement() {
        createWindow("UserManagement", () -> {
            var controller = new UserManagementController(this);
            return new UserManagementView(controller, this);
        });
    }

    public void startBookEdit(Book book) {
        createWindow("BookEdit", () -> {
            var controller = new BookEditController(_bookRepository, _bookRepositoryListener);
            return new BookEditView(controller, book);
        });
    }

    public void startBookLending(Book book) {
        createWindow("BookLending", () -> {
            var controller = new BookLendingController(_bookRepositoryListener);
            return new BookLendingView(controller, book);
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
                public void windowClosed(WindowEvent windowEvent) {
                    openWindows.remove(key);
                }
            });
            view.setVisible(true);  // Ensure the window is visible
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