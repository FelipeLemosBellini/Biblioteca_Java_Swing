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
import infraestructure.IPersistentDataRepository;
import infraestructure.PersistentDataRepository;
import infraestructure.PresentationManager;

import java.time.LocalTime;

public class DependencyManager {
    public void AddDependences()
    {
        var locator = ServiceLocatorImpl.getInstance();

        locator.registerService(BookHibernateDaoImpl.class);
        locator.registerService(IPersistentDataRepository.class, PersistentDataRepository.class);

//        locator.addSingleton(ICurrentUser.class);
//        locator.addSingleton(PresentationManager.class);
//        locator.addSingleton(IBookSubscriber.class);
//        locator.addSingleton(UserObserverImpl.class);
//        locator.addSingleton(IUserRepository.class);
//
//        locator.addScoped(IBookDao.class, BookHibernateDaoImpl.class);
//        locator.addScoped(IBookRepository.class, BookRepositoryImpl.class);
//        locator.addScoped(ILoginDao.class, LoginDaoHibernateImpl.class);
//        locator.addScoped(ILoginRepository.class, LoginRepositoryImpl.class);
//        locator.addScoped(IUserRepository.class, UserHibernateDAOImpl.class);
//        locator.addScoped(MenuController.class, MenuController.class);
//        locator.addScoped(MenuView.class, MenuView.class);
//        locator.addScoped(LoginController.class, LoginController.class);
//        locator.addScoped(LoginView.class, LoginView.class);
//        locator.addScoped(BookLoanController.class, BookLoanController.class);
//        locator.addScoped(BookLoanView.class, BookLoanView.class);
//        locator.addScoped(BooksController.class, BooksController.class);
//        locator.addScoped(BooksView.class, BooksView.class);
//        locator.addScoped(UserManagementController.class, UserManagementController.class);
//        locator.addScoped(UserManagementView.class, UserManagementView.class);
//        locator.addScoped(UserEditController.class, UserEditController.class);
//        locator.addScoped(UserEditView.class, UserEditView.class);
//        locator.addScoped(UserEditPasswordController.class, UserEditPasswordController.class);
//        locator.addScoped(UserEditPasswordView.class, UserEditPasswordView.class);
//        locator.addScoped(BookEditController.class, BookEditController.class);
//        locator.addScoped(BookEditView.class, BookEditView.class);
//        locator.addScoped(IBookNotifier.class, BookObserverImpl.class);
//        locator.addScoped(InformationController.class, InformationController.class);
//        locator.addScoped(InformationView.class, InformationView.class);
    }
}
