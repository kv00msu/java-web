package Connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.imageio.spi.ServiceRegistry;


public class Connect {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        try
        {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            StandardServiceRegistryBuilder serviceBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(serviceBuilder.build());

        }
        catch (HibernateException exception)
        {
            System.out.println("Problem creating session factory");
        }
    }
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
    public static void shutdown() {
        getSessionFactory().close();
    }

}
