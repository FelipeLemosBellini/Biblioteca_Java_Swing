package presentation;

import infrastructure.interfaces.IBookRepository;
import infrastructure.repositories.BookRamMemoryRepository;
import presentation.controller.BookEditController;
import presentation.controller.BookLendingController;
import presentation.controller.HomeController;
import presentation.controller.LibraryController;
import presentation.model.BookRepositoryListener;
import presentation.view.BookEditView;
import presentation.view.BookLendingView;
import presentation.view.HomeView;
import presentation.view.LibraryView;
import core.entities.Book;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class PresentationManager {
    private final BookRepositoryListener _bookRepositoryListener;
    private final IBookRepository _bookRepository;
    private final Map<String, JFrame> openWindows;  // Use JFrame specifically

    public PresentationManager() {
        _bookRepository = new BookRamMemoryRepository();
        _bookRepositoryListener = new BookRepositoryListener();
        openWindows = new HashMap<>();

        startHome();
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
            return new LibraryView(controller);
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