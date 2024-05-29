package construtionSet.dependencyManager;

import construtionSet.dependencyInjection.implementations.DependencyInjectionContainer;
import features.books.dataSources.*;
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
import features.session.CurrentUserImpl;
import features.session.ICurrentUser;
import features.user.datasources.*;
import features.user.presentation.*;
import infraestructure.IPersistentDataRepository;
import infraestructure.PersistentDataRepository;
import infraestructure.PresentationManager;

public class DependencyInjectionManager implements IDependencyInjectionManager {
    public void AddDependences()
    {
        var container = new DependencyInjectionContainer();

        container.addSingleton(ICurrentUser.class, CurrentUserImpl.class);
        container.addSingleton(IBookNotifier.class, BookObserverImpl.class);
        container.addSingleton(IBookSubscriber.class, BookObserverImpl.class);
        container.addSingleton(IUserSubscriber.class, UserObserverImpl.class);
        container.addSingleton(IUserNotifier.class, UserObserverImpl.class);
        container.addSingleton(PresentationManager.class);
        
        container.addScoped(IPersistentDataRepository.class, PersistentDataRepository.class);

        container.addTransient(IBookDao.class, BookHibernateDaoImpl.class);
        container.addTransient(IBookRepository.class, BookRepositoryImpl.class);
        
        container.addTransient(ILoginDao.class, LoginDaoHibernateImpl.class);
        container.addTransient(ILoginRepository.class, LoginRepositoryImpl.class);
        
        container.addTransient(IUserRepository.class, UserHibernateDAOImpl.class);

        
        container.addTransient(MenuController.class);
        container.addTransient(MenuView.class);
        container.addTransient(LoginController.class);
        container.addTransient(LoginView.class);
        container.addTransient(BookLoanController.class);
        container.addTransient(BookLoanView.class);
        container.addTransient(BooksController.class);
        container.addTransient(BooksView.class);
        container.addTransient(UserManagementController.class);
        container.addTransient(UserManagementView.class);
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
