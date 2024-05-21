package infrastructure.repositories;
import core.entities.Employee;
import core.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import static javax.swing.JOptionPane.showMessageDialog;

public class PersistentDataRepository {
    private static SessionFactory sessionFactory;

    public static SessionFactory getDatabaseSessionFactory() {
        return sessionFactory;
    }

    public static void createSessionFactory() {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();
        try {
            sessionFactory = new MetadataSources(registry).addAnnotatedClasses(
//                    Book.class,
                    Employee.class,
                            User.class
//                            ,
//                            Administrator.class
                    )
                    .buildMetadata()
                    .buildSessionFactory();

        } catch (Exception e) {
            showMessageDialog(null, "Erro ao iniciar os dados, tente abrir a aplicação novamente");
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
