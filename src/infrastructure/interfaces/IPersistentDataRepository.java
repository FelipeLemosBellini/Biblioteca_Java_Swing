package infrastructure.interfaces;

import org.hibernate.SessionFactory;

public interface IPersistentDataRepository {

    SessionFactory getDatabaseSessionFactory();

    void createSessionFactory();
}
