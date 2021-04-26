package dao;

import Connection.SessionUI;
import entity.Employees;
import entity.Payments;
import entity.Projects;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.Tuple;
import java.util.List;

public class PaymentsDAO extends SessionUI {

    public int insert(Payments payment) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        session.save(payment);

        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return 0;
    }

    public int remove(Payments payment) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        session.remove(payment);

        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return 0;
    }

    public int update(Payments payment) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        session.update(payment);

        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return 0;
    }

    public Payments getById(Integer id) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM Payments WHERE paymentId = :id";
        Query query = session.createSQLQuery(sql).addEntity(Payments.class);
        query.setParameter("id", id);
        Payments payments = (Payments) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return payments;
    }

    public Payments getByEmployee(Integer id) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM Payments WHERE employeeId = :id";
        Query query = session.createSQLQuery(sql).addEntity(Payments.class);
        query.setParameter("id", id);
        Payments payments = (Payments) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return payments;
    }

    public List<Payments> listOfPayments() {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String hql = "FROM Payments";
        Query query = session.createQuery(hql);
        List<Payments> payments = query.list();
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return payments;
    }
}
