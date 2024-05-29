import construtionSet.dependencyInjection.DependencyInjectionManager;
import construtionSet.dependencyInjection.IDependencyInjectionManager;
import construtionSet.dependencyInjection.IServiceLocator;
import construtionSet.dependencyInjection.ServiceLocatorImpl;
import features.user.datasources.IUserRepository;
import infraestructure.PresentationManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        User employee = new User("login", "senha", EProfile.employee);
//        PersistentDataRepository.createSessionFactory();
//        try {
//            PersistentDataRepository.getDatabaseSessionFactory().inTransaction(session -> {
//                session.persist(employee);
//            });
//            System.out.println("Task inserted successfully.");
//        } catch (Exception e) {
//            System.out.println("Error inserting task: " + e.getMessage());
//        }

//        UserHibernateRepository userHibernateRepository = new UserHibernateRepository(new PresentationManager().startUserHibernateRepository());
//
//        User user = userHibernateRepository.getUser(5);
//        System.out.println(user.getProfile());
//        System.out.println(user.getLogin());
//
//        List<User> userList = userHibernateRepository.searchUser("ff");
//        System.out.println(userList.getFirst().getId());
//
//        userHibernateRepository.removeUser(userList.getFirst());
//        userList = userHibernateRepository.searchUser("ff");
//        System.out.println(userList.getFirst().getId());
        
        IServiceLocator locator = ServiceLocatorImpl.getInstance();
        
        IDependencyInjectionManager manager = new DependencyInjectionManager();
        manager.AddDependences();
        
        var presentationManager = (PresentationManager) locator.getService(PresentationManager.class);
        presentationManager.startLogin();
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            }
        });
//        var manager = new DependencyManager();
//        
//        manager.AddDependences();
//        
//        var locator = ServiceLocatorImpl.getInstance();
//
//        BookHibernateDaoImpl serviceA1 = (BookHibernateDaoImpl) locator.getService(BookHibernateDaoImpl.class);
//        var userRepository = locator.getService(IBookDao.class);
    }
}