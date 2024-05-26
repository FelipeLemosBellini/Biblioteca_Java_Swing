package presentation;

import core.entities.User;
import core.services.AdminPermissions;
import infrastructure.interfaces.IBookRepository;
import infrastructure.interfaces.IUserRepository;
import infrastructure.repositories.BookRamMemoryRepository;
import infrastructure.repositories.PersistentDataRepository;
import infrastructure.repositories.UserHibernateRepository;

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
    private final BookRepositoryListener _bookRepositoryListener;
    private final UserRepositoryListener _userRepositoryListener;

    private final IBookRepository _bookRepository;
    private final IUserRepository _userRepository;

    private User _currentUser;

    private final Map<String, JFrame> openWindows;  // Use JFrame specifically

    public PresentationManager() {
        _bookRepository = new BookRamMemoryRepository();
        _bookRepositoryListener = new BookRepositoryListener();

        _userRepository = new UserHibernateRepository(startUserHibernateRepository());
        _userRepositoryListener = new UserRepositoryListener();

        openWindows = new HashMap<>();

        startLogin();
    }

    public PersistentDataRepository startUserHibernateRepository() {
        return new PersistentDataRepository();
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
            return new HomeView(controller);
        });
    }

    public void startLibrary() {
        createWindow("Library", () -> {
            var controller = new LibraryController(this, _bookRepository, _bookRepositoryListener);
            return new LibraryView(controller, this);
        });
    }

    public void startUserManagement() {
        if (AdminPermissions.verifyAdminUser(_currentUser)) {
            createWindow("UserManagement", () -> {
                var controller = new UserManagementController(this, _userRepository, _userRepositoryListener);
                return new UserManagementView(controller, this);
            });
        } else
            startInformationWindow("Usuário sem permissão de acesso");
    }

    public void startUserEdit(User user) {
        createWindow("UserEdit", () -> {
            var controller = new UserEditController(this, _userRepository, _userRepositoryListener);
            return new UserEditView(controller, user);
        });
    }

    public void startUserPasswordEdit(User user) {
        createWindow("UserEditPassword", () -> {
            var controller = new UserEditPasswordController(this, _userRepositoryListener);
            return new UserEditPasswordView(controller, user);
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

    public void startInformationWindow(String message) {
        createWindow("Information_" + message.replace(" ", "_"), () -> {
            var controller = new InformationController(this);
            return new InformationView(controller, message);
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