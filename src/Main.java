import dependencyInjection.DependencyManager;
import dependencyInjection.ServiceLocator;
import dependencyInjection.ServiceLocatorImpl;
import features.books.dataSources.BookHibernateDaoImpl;
import features.books.dataSources.IBookDao;
import features.session.ICurrentUser;
import features.user.datasources.IUserRepository;

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

        IUserRepository repository = ServiceLocator.getInstance().getUserRepository();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ServiceLocator.getInstance().getPresentationManager().startLogin();
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