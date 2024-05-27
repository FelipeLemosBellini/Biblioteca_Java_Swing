package presentation;

import core.entities.Book;
import core.entities.User;
import core.interfaces.ICurrentUser;
import core.services.CurrentUserImpl;
import infrastructure.interfaces.IBookRepository;
import infrastructure.interfaces.IUserRepository;
import infrastructure.repositories.*;
import presentation.controller.*;
import presentation.model.BookRepositoryListener;
import presentation.model.UserRepositoryListener;
import presentation.view.*;

public class ServiceLocator {
    private static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if(instance == null) {
            instance = new ServiceLocator();
        }

        return instance;
    }

    private ICurrentUser currentUser;
    public ICurrentUser getCurrentUser() {
        if(currentUser == null) {
            currentUser = new CurrentUserImpl();
        }

        return currentUser;
    }

    private PresentationManager presentationManager;
    public PresentationManager getPresentationManager() {
        if(presentationManager == null) {
            presentationManager = new PresentationManager(getCurrentUser());
        }

        return presentationManager;
    }

    private BookRepositoryListener bookRepositoryListener;
    public BookRepositoryListener getBookRepositoryListener() {
        if(bookRepositoryListener == null) {
            bookRepositoryListener = new BookRepositoryListener();
        }

        return bookRepositoryListener;
    }

    private UserRepositoryListener userRepositoryListener;
    public UserRepositoryListener getUserRepositoryListener() {
        if(userRepositoryListener == null) {
            userRepositoryListener = new UserRepositoryListener();
        }

        return userRepositoryListener;
    }
    
    public PersistentDataRepository getPersistentDataRepository() {
        return new PersistentDataRepository();
    }
    
    private IUserRepository userRepository;
    public IUserRepository getUserRepository() {
        if(userRepository == null) {
//            userRepository = new UserRamMemoryRepository();
            userRepository = new UserHibernateRepository(getPersistentDataRepository());
        }

        return userRepository;
    }
    
    private IBookRepository bookRepository;
    public IBookRepository getBookRepository() {
        if(bookRepository == null) {
//            bookRepository = new BookRamMemoryRepository();
            bookRepository = new BookHibernateRepository(getPersistentDataRepository());
        }

        return bookRepository;
    }

    public HomeController getHomeController() {
        return new HomeController(getPresentationManager());
    }

    public HomeView getHomeView() {
        return new HomeView(getHomeController());
    }
    
    public LoginController getLoginController() {
        return new LoginController(getPresentationManager(), getUserRepository(), getCurrentUser());
    }
    
    public LoginView getLoginView() {
        return new LoginView(getLoginController());
    }
    
    public LibraryController getLibraryController() {
        return new LibraryController(getPresentationManager(), getBookRepository(), getBookRepositoryListener());
    }
    
    public LibraryView getLibraryView() {
        return new LibraryView(getLibraryController(), getPresentationManager());
    }
    
    public UserManagementController getUserManagementController(){
        return new UserManagementController(getPresentationManager(), getUserRepository(), getUserRepositoryListener());
    }
    
    public UserManagementView getUserManagementView() {
        return  new UserManagementView(getUserManagementController(), getPresentationManager());
    }
    
    public UserEditController getUserEditController(){
        return new UserEditController(getPresentationManager(), getUserRepository(), getUserRepositoryListener());
    }
    
    public UserEditView getUserEditView() {
        return new UserEditView(getUserEditController(), getCurrentUser());
    }
    
    public UserEditPasswordController getUserEditPasswordController(){
        return new UserEditPasswordController(getPresentationManager(), getUserRepositoryListener(), getUserRepository());
    }
    
    public UserEditPasswordView getUserEditPasswordView() {
        return new UserEditPasswordView(getUserEditPasswordController(), getCurrentUser());
    }
    
    public BookEditController getBookEditController(){
        return new BookEditController(getBookRepository(), getBookRepositoryListener());
    }
    
    public BookEditView getBookEditView(Book book) {
        return new BookEditView(getBookEditController(), book);
    }
    
    public BookLendingController getBookLendingController(){
        return new BookLendingController(getBookRepositoryListener(), getBookRepository());
    }
    
    public BookLendingView getBookLendingView(Book book) {
        return new BookLendingView(getBookLendingController(), book);
    }
    
    public InformationController getInformationController() {
        return new InformationController(getPresentationManager());
    }
    
    public InformationView getInformationView(String message) {
        return new InformationView(getInformationController(), message);
    }
}
