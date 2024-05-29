package construtionSet.dependencyManager;

import construtionSet.dependencyInjection.implementations.DependencyInjectionContainer;
import features.books.dataSources.*;
import features.books.presentation.controllers.BookEditController;
import features.books.presentation.views.BookEditView;
import features.books.presentation.controllers.BookLoanController;
import features.books.presentation.views.BookLoanView;
import features.menu.MenuController;
import features.menu.MenuView;
import features.messageInformer.InformationController;
import features.messageInformer.InformationView;
import features.books.presentation.controllers.BooksController;
import features.books.presentation.views.BooksView;
import features.login.datasources.ILoginDao;
import features.login.datasources.ILoginRepository;
import features.login.datasources.LoginDaoHibernateImpl;
import features.login.datasources.LoginRepositoryImpl;
import features.login.presentation.LoginController;
import features.login.presentation.LoginView;
import features.session.CurrentUserImpl;
import features.session.ICurrentUser;
import features.user.datasources.*;
import features.user.presentation.controllers.UserEditController;
import features.user.presentation.controllers.UserEditPasswordController;
import features.user.presentation.controllers.UsersController;
import features.user.presentation.views.UserEditPasswordView;
import features.user.presentation.views.UserEditView;
import features.user.presentation.views.UsersView;
import infraestructure.IPersistentDataRepository;
import infraestructure.PersistentDataRepository;
import infraestructure.PresentationManager;

public class DependencyInjectionManager implements IDependencyInjectionManager {
    public void AddDependences()
    {
        var container = new DependencyInjectionContainer();

        container.addSingleton(PresentationManager.class);
        container.addSingleton(ICurrentUser.class, CurrentUserImpl.class);
        container.addSingleton(IBookNotifier.class, BookObserverImpl.class);
        container.addSingleton(IBookSubscriber.class, BookObserverImpl.class);
        container.addSingleton(IUserSubscriber.class, UserObserverImpl.class);
        container.addSingleton(IUserNotifier.class, UserObserverImpl.class);
        
        container.addScoped(IPersistentDataRepository.class, PersistentDataRepository.class);
        container.addScoped(IBookDao.class, BookHibernateDaoImpl.class);
        container.addScoped(IBookRepository.class, BookRepositoryImpl.class);
        container.addScoped(ILoginDao.class, LoginDaoHibernateImpl.class);
        container.addScoped(ILoginRepository.class, LoginRepositoryImpl.class);
        container.addScoped(IUserDao.class, UserHibernateDAOImpl.class);
        container.addScoped(IUserRepository.class, UserRepositoryImpl.class);

        container.addTransient(MenuController.class);
        container.addTransient(MenuView.class);
        
        container.addTransient(LoginController.class);
        container.addTransient(LoginView.class);
        
        container.addTransient(BookLoanController.class);
        container.addTransient(BookLoanView.class);
        
        container.addTransient(BooksController.class);
        container.addTransient(BooksView.class);
        
        container.addTransient(UsersController.class);
        container.addTransient(UsersView.class);
        
        container.addTransient(UserEditController.class);
        container.addTransient(UserEditView.class);
        
        container.addTransient(UserEditPasswordController.class);
        container.addTransient(UserEditPasswordView.class);
        
        container.addTransient(BookEditController.class);
        container.addTransient(BookEditView.class);
        
        container.addTransient(InformationController.class);
        container.addTransient(InformationView.class);
        
        
    }
}
