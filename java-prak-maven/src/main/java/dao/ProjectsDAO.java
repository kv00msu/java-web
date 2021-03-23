package dao;

import Connection.SessionUI;
import entity.Employees;
import entity.Employees_and_Projects;
import entity.Payments;
import entity.Projects;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProjectsDAO extends SessionUI {
    public int insert(Projects projects) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(projects);

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

    public int remove(Projects projects) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.remove(projects);

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

    public int update(Projects projects) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(projects);

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

    public Projects infoAboutProject(Integer id) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        try {
            session.beginTransaction();
            String sql = "SELECT * FROM Projects WHERE projectId = :id";
            Query query = session.createSQLQuery(sql).addEntity(Projects.class);
            query.setParameter("id", id);
            Projects project = (Projects) query.getSingleResult();
            session.getTransaction().commit();
            session.close();
            sessionUI.getSessionFactory().close();
            return project;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            session.close();
            sessionUI.getSessionFactory().close();
            return null;
        }
    }

    public List<Projects> listOfProjects() {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        try {
            session.beginTransaction();
            String hql = "FROM Projects";
            Query query = session.createQuery(hql);
            List<Projects> projects = query.list();
            session.getTransaction().commit();
            session.close();
            sessionUI.getSessionFactory().close();
            return projects;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            session.close();
            sessionUI.getSessionFactory().close();
            return null;
        }
    }

    public List<Employees> listOfEmployeesInProject(Integer id){
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        try {
            session.beginTransaction();
            String sql = "SELECT s.* FROM Employees s JOIN Employees_and_Projects a ON s.employeeId = a.employeeId WHERE a.projectId = :id";
            Query query = session.createSQLQuery(sql).addEntity(Employees.class);
            query.setParameter("id", id);
            List<Employees> employees = (List<Employees>) query.list();
            session.getTransaction().commit();
            session.close();
            sessionUI.getSessionFactory().close();
            return employees;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            session.close();
            sessionUI.getSessionFactory().close();
            return null;
        }
    }

    public List<Employees_and_Projects> listOfPositionInProject(Integer id) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        try {
            session.beginTransaction();
            String sql = "SELECT * FROM Employees_and_Projects WHERE projectId = :id";
            Query query = session.createSQLQuery(sql).addEntity(Employees_and_Projects.class);
            query.setParameter("id", id);
            List<Employees_and_Projects> positions = query.list();
            session.getTransaction().commit();
            session.close();
            sessionUI.getSessionFactory().close();
            return positions;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            session.close();
            sessionUI.getSessionFactory().close();
            return null;
        }
    }
}
