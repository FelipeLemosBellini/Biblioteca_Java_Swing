import construtionSet.dependencyManager.DependencyInjectionManager;
import construtionSet.dependencyManager.IDependencyInjectionManager;
import construtionSet.dependencyInjection.contracts.IServiceLocator;
import construtionSet.dependencyInjection.implementations.ServiceLocatorImpl;
import features.user.datasources.IUserRepository;
import features.user.datasources.UserRepositoryImpl;
import features.user.entities.EProfileEntity;
import features.user.entities.UserEntity;
import infraestructure.IPresentationManager;
import infraestructure.PresentationManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        IServiceLocator locator = ServiceLocatorImpl.getInstance();

        IDependencyInjectionManager manager = new DependencyInjectionManager();
        manager.AddDependences();

        seedDatabase(locator);

        var presentationManager = (IPresentationManager) locator.getService(IPresentationManager.class);
        presentationManager.startLogin();
    }

    private static void seedDatabase(IServiceLocator locator) {
        var userRepository = (IUserRepository)locator.getService(IUserRepository.class);
        
        var defaultAdmin = userRepository.getUser("admin");
        if(defaultAdmin == null)
            userRepository.addUser(new UserEntity("admin", "admin", EProfileEntity.admin));

        var defaultEmployee = userRepository.getUser("employee");
        if(defaultEmployee == null)
            userRepository.addUser(new UserEntity("employee", "employee", EProfileEntity.employee));

        userRepository = null;
        defaultAdmin = null;
        defaultEmployee = null;
    }
}