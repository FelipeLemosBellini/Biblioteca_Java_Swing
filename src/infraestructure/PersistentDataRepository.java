package infraestructure;

import features.books.entities.BookEntity;
import features.user.entities.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import static javax.swing.JOptionPane.showMessageDialog;

public class PersistentDataRepository implements IPersistentDataRepository {
    private static SessionFactory sessionFactory;

    @Override
    public SessionFactory getDatabaseSessionFactory() {
        sessionFactory = sessionFactory == null ? createSessionFactory() : sessionFactory;
        return sessionFactory;
    }

    private SessionFactory createSessionFactory() {
        SessionFactory newSessionFactory = null;
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();
        try {
            newSessionFactory = new MetadataSources(registry).addAnnotatedClasses(
                            BookEntity.class,
                            UserEntity.class
                    )
                    .buildMetadata()
                    .buildSessionFactory();

        } catch (Exception e) {
            showMessageDialog(null, "Erro ao iniciar os dados, tente abrir a aplicação novamente");
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return newSessionFactory;
    }
}
