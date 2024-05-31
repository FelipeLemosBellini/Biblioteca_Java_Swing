package construtionSet.dependencyManager;

import construtionSet.dependencyInjection.contracts.IDependencyInjectionContainer;
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
    IDependencyInjectionContainer _dependencyInjectionContainer;
    
    public DependencyInjectionManager(IDependencyInjectionContainer dependencyInjectionContainer){
        _dependencyInjectionContainer = dependencyInjectionContainer;
    } 
    
    public void AddDependences()
    {
        _dependencyInjectionContainer.addSingleton(IPresentationManager.class, PresentationManager.class);
        _dependencyInjectionContainer.addSingleton(ICurrentUser.class, CurrentUserImpl.class);
        _dependencyInjectionContainer.addSingleton(IBookNotifier.class, BookObserverImpl.class);
        _dependencyInjectionContainer.addSingleton(IBookSubscriber.class, BookObserverImpl.class);
        _dependencyInjectionContainer.addSingleton(IUserSubscriber.class, UserObserverImpl.class);
        _dependencyInjectionContainer.addSingleton(IUserNotifier.class, UserObserverImpl.class);

        _dependencyInjectionContainer.addScoped(IPersistenceObject.class, PersistenceObjectImpl.class);
        _dependencyInjectionContainer.addScoped(IBookDao.class, BookHibernateDaoImpl.class);
        _dependencyInjectionContainer.addScoped(IBookRepository.class, BookRepositoryImpl.class);
        _dependencyInjectionContainer.addScoped(ILoginDao.class, LoginDaoHibernateImpl.class);
        _dependencyInjectionContainer.addScoped(ILoginRepository.class, LoginRepositoryImpl.class);
        _dependencyInjectionContainer.addScoped(IUserDao.class, UserHibernateDAOImpl.class);
        _dependencyInjectionContainer.addScoped(IUserRepository.class, UserRepositoryImpl.class);

        _dependencyInjectionContainer.addTransient(IUsersController.class, UsersController.class);
        _dependencyInjectionContainer.addTransient(IUsersView.class, UsersView.class);
        _dependencyInjectionContainer.addTransient(IUserEditController.class, UserEditController.class);
        _dependencyInjectionContainer.addTransient(IUserEditView.class, UserEditView.class);
        _dependencyInjectionContainer.addTransient(IUserEditPasswordController.class, UserEditPasswordController.class);
        _dependencyInjectionContainer.addTransient(IUserEditPasswordView.class, UserEditPasswordView.class);
        _dependencyInjectionContainer.addTransient(ILoginController.class, LoginController.class);
        _dependencyInjectionContainer.addTransient(ILoginView.class, LoginView.class);
        _dependencyInjectionContainer.addTransient(IBookLoanController.class, BookLoanController.class);
        _dependencyInjectionContainer.addTransient(IBookLoanView.class, BookLoanView.class);
        _dependencyInjectionContainer.addTransient(IBooksController.class, BooksController.class);
        _dependencyInjectionContainer.addTransient(IBooksView.class, BooksView.class);
        _dependencyInjectionContainer.addTransient(IBookEditController.class, BookEditController.class);
        _dependencyInjectionContainer.addTransient(IBookEditView.class, BookEditView.class);
        _dependencyInjectionContainer.addTransient(IMenuController.class, MenuController.class);
        _dependencyInjectionContainer.addTransient(IMenuView.class, MenuView.class);
        _dependencyInjectionContainer.addTransient(IInformationController.class, InformationController.class);
        _dependencyInjectionContainer.addTransient(IInformationView.class, InformationView.class);
    }
}
