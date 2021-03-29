package Connection;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionUI {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory createSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;

    }

}
