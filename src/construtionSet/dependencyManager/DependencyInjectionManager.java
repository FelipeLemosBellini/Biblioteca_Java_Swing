package construtionSet.dependencyManager;

import construtionSet.dependencyInjection.implementations.DependencyInjectionContainer;
import features.books.dataSources.*;
import features.books.presentation.controllers.*;
import features.books.presentation.views.*;
import features.login.presentation.ILoginController;
import features.login.presentation.ILoginView;
import features.menu.IMenuController;
import features.menu.IMenuView;
import features.menu.MenuController;
import features.menu.MenuView;
import features.messageInformer.IInformationController;
import features.messageInformer.IInformationView;
import features.messageInformer.InformationController;
import features.messageInformer.InformationView;
import features.login.datasources.ILoginDao;
import features.login.datasources.ILoginRepository;
import features.login.datasources.LoginDaoHibernateImpl;
import features.login.datasources.LoginRepositoryImpl;
import features.login.presentation.LoginController;
import features.login.presentation.LoginView;
import features.session.CurrentUserImpl;
import features.session.ICurrentUser;
import features.user.datasources.*;
import features.user.presentation.controllers.*;
import features.user.presentation.views.*;
import infraestructure.IPersistenceObject;
import infraestructure.IPresentationManager;
import infraestructure.PersistenceObjectImpl;
import infraestructure.PresentationManager;

public class DependencyInjectionManager implements IDependencyInjectionManager {
    public void AddDependences()
    {
        var container = new DependencyInjectionContainer();

        container.addSingleton(IPresentationManager.class, PresentationManager.class);
        container.addSingleton(ICurrentUser.class, CurrentUserImpl.class);
        container.addSingleton(IBookNotifier.class, BookObserverImpl.class);
        container.addSingleton(IBookSubscriber.class, BookObserverImpl.class);
        container.addSingleton(IUserSubscriber.class, UserObserverImpl.class);
        container.addSingleton(IUserNotifier.class, UserObserverImpl.class);
        
        container.addScoped(IPersistenceObject.class, PersistenceObjectImpl.class);
        container.addScoped(IBookDao.class, BookHibernateDaoImpl.class);
        container.addScoped(IBookRepository.class, BookRepositoryImpl.class);
        container.addScoped(ILoginDao.class, LoginDaoHibernateImpl.class);
        container.addScoped(ILoginRepository.class, LoginRepositoryImpl.class);
        container.addScoped(IUserDao.class, UserHibernateDAOImpl.class);
        container.addScoped(IUserRepository.class, UserRepositoryImpl.class);

        container.addTransient(IUsersController.class, UsersController.class);
        container.addTransient(IUsersView.class, UsersView.class);
        container.addTransient(IUserEditController.class, UserEditController.class);
        container.addTransient(IUserEditView.class, UserEditView.class);
        container.addTransient(IUserEditPasswordController.class, UserEditPasswordController.class);
        container.addTransient(IUserEditPasswordView.class, UserEditPasswordView.class);
        container.addTransient(ILoginController.class, LoginController.class);
        container.addTransient(ILoginView.class, LoginView.class);
        container.addTransient(IBookLoanController.class, BookLoanController.class);
        container.addTransient(IBookLoanView.class, BookLoanView.class);
        container.addTransient(IBooksController.class, BooksController.class);
        container.addTransient(IBooksView.class, BooksView.class);
        container.addTransient(IBookEditController.class, BookEditController.class);
        container.addTransient(IBookEditView.class, BookEditView.class);
        container.addTransient(IMenuController.class, MenuController.class);
        container.addTransient(IMenuView.class, MenuView.class);
        container.addTransient(IInformationController.class, InformationController.class);
        container.addTransient(IInformationView.class, InformationView.class);
        
        
    }
}
