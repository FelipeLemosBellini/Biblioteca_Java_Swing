import core.entities.Employee;
import core.entities.User;
import core.enums.EProfile;
import infrastructure.repositories.PersistentDataRepository;
import org.hibernate.SessionFactory;
import presentation.PresentationManager;

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

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PresentationManager();
            }
        });
    }
}