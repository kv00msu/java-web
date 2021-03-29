package dao;

import Connection.SessionUI;
import entity.Employees;
import entity.Employees_and_Projects;
import entity.Payments;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Employees_and_ProjectsDAO extends SessionUI {


    public Employees_and_ProjectsDAO() {

    }

    public int insert(Employees_and_Projects employees_and_projects) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        session.save(employees_and_projects);

        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return 0;
    }

    public int remove(Employees_and_Projects employees_and_projects) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String sql = "DELETE FROM Employees_and_Projects CASCADE WHERE id = :id";
        Query query = session.createNativeQuery(sql).addEntity(Employees.class);
        query.setParameter("id", employees_and_projects.getId());
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return 0;
    }

    public int update(Employees_and_Projects employees_and_projects) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        session.update(employees_and_projects);

        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return 0;
    }

    public List<Employees_and_Projects> listOfEmployees_and_Projects() {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String hql = "FROM Employees_and_Projects";
        Query query = session.createQuery(hql);
        List<Employees_and_Projects> employees_and_projects = query.list();
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return employees_and_projects;
    }
}
