package infraestructure;

import org.hibernate.SessionFactory;

public interface IPersistentDataRepository {

    SessionFactory getDatabaseSessionFactory();
}
