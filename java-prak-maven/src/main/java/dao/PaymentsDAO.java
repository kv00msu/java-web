package dao;

import Connection.SessionUI;
import entity.Employees;
import entity.Payments;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.Tuple;
import java.util.List;

public class PaymentsDAO extends SessionUI {

    public int insert(Payments payment) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(payment);

            session.getTransaction().commit();
            session.close();
            sessionUI.getSessionFactory().close();
            return 0;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            session.close();
            sessionUI.getSessionFactory().close();
            return 1;
        }
    }

    public int remove(Payments payment) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.remove(payment);

            session.getTransaction().commit();
            session.close();
            sessionUI.getSessionFactory().close();
            return 0;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            session.close();
            sessionUI.getSessionFactory().close();
            return 1;
        }
    }

    public int update(Payments payment) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(payment);

            session.getTransaction().commit();
            session.close();
            sessionUI.getSessionFactory().close();
            return 0;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            session.close();
            sessionUI.getSessionFactory().close();
            return 1;
        }
    }

    public List<Payments> listOfPayments() {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        try {
            session.beginTransaction();
            String hql = "FROM Payments";
            Query query = session.createQuery(hql);
            List<Payments> payments = query.list();
            session.getTransaction().commit();
            session.close();
            sessionUI.getSessionFactory().close();
            return payments;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.close();
            sessionUI.getSessionFactory().close();
            return null;
        }
    }
}
