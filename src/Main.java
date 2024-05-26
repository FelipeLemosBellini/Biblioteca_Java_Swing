import core.entities.Employee;
import core.entities.User;
import core.enums.EProfile;
import infrastructure.repositories.PersistentDataRepository;
import infrastructure.repositories.UserHibernateRepository;
import org.hibernate.SessionFactory;
import presentation.PresentationManager;

import javax.swing.*;
import java.util.List;

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


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PresentationManager();
            }
        });
    }
}