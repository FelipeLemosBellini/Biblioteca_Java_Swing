package dependencyInjection;

import features.books.dataSources.*;
import features.books.entities.BookEntity;
import features.books.presentation.BookEditController;
import features.books.presentation.BookEditView;
import features.books.presentation.BookLoanController;
import features.books.presentation.BookLoanView;
import features.menu.MenuController;
import features.menu.MenuView;
import features.messageInformer.InformationController;
import features.messageInformer.InformationView;
import features.books.presentation.BooksController;
import features.books.presentation.BooksView;
import features.login.datasources.ILoginDao;
import features.login.datasources.ILoginRepository;
import features.login.datasources.LoginDaoHibernateImpl;
import features.login.datasources.LoginRepositoryImpl;
import features.login.presentation.LoginController;
import features.login.presentation.LoginView;
import features.session.ICurrentUser;
import features.session.CurrentUserImpl;
import features.user.datasources.IUserRepository;
import features.user.datasources.UserHibernateDAOImpl;
import features.user.datasources.UserObserverImpl;
import features.user.presentation.*;
import infraestructure.PersistentDataRepository;
import infraestructure.PresentationManager;

public class ServiceLocator {
    private static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }

        return instance;
    }

    private ICurrentUser currentUser;

    public ICurrentUser getCurrentUser() {
        if (currentUser == null) {
            currentUser = new CurrentUserImpl();
        }

        return currentUser;
    }

    private PresentationManager presentationManager;

    public PresentationManager getPresentationManager() {
        if (presentationManager == null) {
            presentationManager = new PresentationManager(getCurrentUser());
        }

        return presentationManager;
    }

    private IBookSubscriber bookSubscriber;

    public IBookSubscriber getBookSubscriber() {
        if (bookSubscriber == null) {
            bookSubscriber = new BookObserverImpl();
        }

        return bookSubscriber;
    }

    private UserObserverImpl userObserverImpl;

    public UserObserverImpl getUserRepositoryListener() {
        if (userObserverImpl == null) {
            userObserverImpl = new UserObserverImpl();
        }

        return userObserverImpl;
    }

    public PersistentDataRepository getPersistentDataRepository() {
        return new PersistentDataRepository();
    }

    private IUserRepository userRepository;

    public IUserRepository getUserRepository() {
        if (userRepository == null) {
//            userRepository = new UserRamMemoryRepository();
            userRepository = new UserHibernateDAOImpl(getPersistentDataRepository());
        }

        return userRepository;
    }
    
    public IBookDao getBookDao() {
        return new BookHibernateDaoImpl(getPersistentDataRepository());
    }
    
    public IBookRepository getBookRepository() {
        return new BookRepositoryImpl(getBookDao());
    }

    public MenuController getHomeController() {
        return new MenuController(getPresentationManager());
    }

    public MenuView getHomeView() {
        return new MenuView(getHomeController());
    }

    public ILoginDao getLoginDao() {
        return new LoginDaoHibernateImpl(getPersistentDataRepository());
    }

    public ILoginRepository getLoginRepository() {
        return new LoginRepositoryImpl(getLoginDao());
    }

    public LoginController getLoginController() {
        return new LoginController(getPresentationManager(), getLoginRepository(), getCurrentUser());
    }

    public LoginView getLoginView() {
        return new LoginView(getLoginController());
    }

    public BookLoanController getBookLoanController() {
        return new BookLoanController(getBookNotifier(), getBookRepository());
    }
    
    public BookLoanView getBookLoanView(BookEntity bookEntity) {
        return new BookLoanView(getBookLoanController(), bookEntity);
    }

    public BooksController getLibraryController() {
        return new BooksController(getPresentationManager(), getBookRepository(), getBookSubscriber());
    }

    public BooksView getLibraryView() {
        return new BooksView(getLibraryController(), getPresentationManager());
    }

    public UserManagementController getUserManagementController() {
        return new UserManagementController(getPresentationManager(), getUserRepository(), getUserRepositoryListener());
    }

    public UserManagementView getUserManagementView() {
        return new UserManagementView(getUserManagementController(), getPresentationManager());
    }

    public UserEditController getUserEditController() {
        return new UserEditController(getPresentationManager(), getUserRepository(), getUserRepositoryListener());
    }

    public UserEditView getUserEditView() {
        return new UserEditView(getUserEditController(), getCurrentUser());
    }

    public UserEditPasswordController getUserEditPasswordController() {
        return new UserEditPasswordController(getPresentationManager(), getUserRepositoryListener(), getUserRepository());
    }

    public UserEditPasswordView getUserEditPasswordView() {
        return new UserEditPasswordView(getUserEditPasswordController(), getCurrentUser());
    }

    public BookEditController getBookEditController() {
        return new BookEditController(getPresentationManager(), getBookRepository(), getBookNotifier(), getBookSubscriber());
    }

    public BookEditView getBookEditView(BookEntity bookEntity) {
        return new BookEditView(getBookEditController(), bookEntity);
    }

    public IBookNotifier getBookNotifier() {
        return new BookObserverImpl();
    }

    public InformationController getInformationController() {
        return new InformationController(getPresentationManager());
    }

    public InformationView getInformationView(String message) {
        return new InformationView(getInformationController(), message);
    }
}
