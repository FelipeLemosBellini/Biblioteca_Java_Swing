package dependencyInjection;

import features.book.dataSources.*;
import features.book.entities.BookEntity;
import features.book.presentation.BookEditController;
import features.book.presentation.BookEditView;
import features.bookLoan.dataSources.BookLoanRepositoryImpl;
import features.bookLoan.dataSources.IBookLoanRepository;
import features.bookLoan.presentation.BookLoanController;
import features.bookLoan.presentation.BookLoanView;
import features.home.HomeController;
import features.home.HomeView;
import features.informer.InformationController;
import features.informer.InformationView;
import features.library.datasources.ILibraryDao;
import features.library.datasources.ILibraryRepository;
import features.library.datasources.LibraryDaoHibernateImpl;
import features.library.datasources.LibraryRepositoryImpl;
import features.library.presentation.LibraryController;
import features.library.presentation.LibraryView;
import features.login.datasources.ILoginDao;
import features.login.datasources.ILoginRepository;
import features.login.datasources.LoginDaoHibernateImpl;
import features.login.datasources.LoginRepositoryImpl;
import features.login.presentation.LoginController;
import features.login.presentation.LoginView;
import features.currentUser.ICurrentUser;
import features.currentUser.CurrentUserImpl;
import features.user.datasources.IUserRepository;
import features.user.datasources.UserHibernateDAO;
import features.user.datasources.UserRepositoryListener;
import features.user.presentation.*;
import infraestructure.PersistentDataRepository;
import infraestructure.PresentationManager;

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

    private IBookSubscriber bookSubscriber;
    public IBookSubscriber getBookSubscriber() {
        if(bookSubscriber == null) {
            bookSubscriber = new BookListener();
        }

        return bookSubscriber;
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

    public ILoginDao getLoginDao(){
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

    public ILibraryDao getLibraryDao() {
        return new LibraryDaoHibernateImpl(getPersistentDataRepository());
    }
    
    public ILibraryRepository getLibraryRepository() {
        return new LibraryRepositoryImpl(getLibraryDao());
    }
    
    public LibraryController getLibraryController() {
        return new LibraryController(getPresentationManager(), getLibraryRepository(), getBookSubscriber());
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
        return new BookEditController(getPresentationManager(), getBookRepository(), getBookNotifier(), getBookSubscriber());
    }
    
    public BookEditView getBookEditView(BookEntity bookEntity) {
        return new BookEditView(getBookEditController(), bookEntity);
    }
    
    public IBookNotifier getBookNotifier(){
        return new BookListener();
    }
    
    public IBookLoanRepository getBookLoanRepository() {
        return new BookLoanRepositoryImpl();
    }
    
    public BookLoanController getBookLendingController(){
        return new BookLoanController(getBookNotifier(), getBookLoanRepository());
    }
    
    public BookLoanView getBookLendingView(BookEntity bookEntity) {
        return new BookLoanView(getBookLendingController(), bookEntity);
    }
    
    public InformationController getInformationController() {
        return new InformationController(getPresentationManager());
    }
    
    public InformationView getInformationView(String message) {
        return new InformationView(getInformationController(), message);
    }
}
