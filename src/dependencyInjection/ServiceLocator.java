package dependencyInjection;

import book.datasources.BookHibernateRepository;
import book.datasources.IBookRepository;
import book.entities.BookEntity;
import book.presentation.BookEditController;
import book.presentation.BookEditView;
import book.presentation.BookLendingController;
import book.presentation.BookLendingView;
import home.presentation.HomeController;
import home.presentation.HomeView;
import informer.InformationController;
import informer.InformationView;
import library.presentation.LibraryController;
import library.presentation.LibraryView;
import login.presentation.LoginController;
import login.presentation.LoginView;
import user.services.ICurrentUser;
import user.services.CurrentUserImpl;
import book.datasources.BookRepositoryListener;
import user.datasources.IUserRepository;
import user.datasources.UserHibernateDAO;
import user.datasources.UserRepositoryListener;
import user.presentation.*;
import hibernate.PersistentDataRepository;
import utils.PresentationManager;

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
            userRepository = new UserHibernateDAO(getPersistentDataRepository());
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
    
    public BookEditView getBookEditView(BookEntity bookEntity) {
        return new BookEditView(getBookEditController(), bookEntity);
    }
    
    public BookLendingController getBookLendingController(){
        return new BookLendingController(getBookRepositoryListener(), getBookRepository());
    }
    
    public BookLendingView getBookLendingView(BookEntity bookEntity) {
        return new BookLendingView(getBookLendingController(), bookEntity);
    }
    
    public InformationController getInformationController() {
        return new InformationController(getPresentationManager());
    }
    
    public InformationView getInformationView(String message) {
        return new InformationView(getInformationController(), message);
    }
}
