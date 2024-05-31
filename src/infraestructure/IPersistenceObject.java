package infraestructure;

import org.hibernate.SessionFactory;

public interface IPersistenceObject {
    SessionFactory getDatabaseSession();
}
