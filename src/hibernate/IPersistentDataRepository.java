package hibernate;

import org.hibernate.SessionFactory;

public interface IPersistentDataRepository {

    SessionFactory getDatabaseSessionFactory();
}
