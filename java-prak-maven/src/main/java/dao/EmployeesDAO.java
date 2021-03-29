package dao;

import Connection.SessionUI;
import entity.Employees;
import entity.Payments;
import entity.Projects;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class EmployeesDAO extends SessionUI {

    public EmployeesDAO() {

    }

    public int insert(Employees employee) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        session.save(employee);

        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return 0;
    }

    public int remove(Employees employee) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        session.remove(employee);

        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return 0;
    }


    public int update(Employees employee) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        session.update(employee);

        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return 0;
    }

    public List<Employees> getByPosition(String str) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String sql = "SELECT Employees.employeeId, full_name, home_address, education, work_experience, date_of_birth FROM Employees LEFT JOIN Employees_and_Projects ON Employees.employeeID = Employees_and_Projects.employeeId  WHERE Employees_and_Projects.position_in_project = :str";
        Query query = session.createNativeQuery(sql).addEntity(Employees.class);
        query.setParameter("str", str);
        List<Employees> employees = query.list();
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return employees;
    }

    public List<Employees> getByWorkExperience(String str) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String sql = "SELECT s.* FROM Employees s WHERE s.work_experience = :str";
        Query query = session.createNativeQuery(sql).addEntity(Employees.class);
        query.setParameter("str", str);
        List<Employees> employees = query.list();
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return employees;
    }

    public List<Employees> getByProjectName(String str) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String sql = "SELECT Employees.employeeId, full_name, home_address, education, work_experience, date_of_birth FROM Employees " +
                "LEFT JOIN Employees_and_Projects ON Employees.employeeID = Employees_and_Projects.employeeId " +
                "JOIN Projects ON Projects.projectID = Employees_and_Projects.projectId " +
                "WHERE project_name = :str";
        Query query = session.createNativeQuery(sql).addEntity(Employees.class);
        query.setParameter("str", str);
        List<Employees> employees = query.list();
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return employees;
    }

    public List<Employees> listOfEmployees() {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String hql = "FROM Employees";
        Query query = session.createQuery(hql);
        List<Employees> employees = query.list();
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return employees;
    }


    public Employees infoAboutEmployee(Integer id) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String sql = "SELECT Employees.* FROM Employees WHERE Employees.employeeId = :id";
        Query query = session.createSQLQuery(sql).addEntity(Employees.class);
        query.setParameter("id", id);
        Employees employee = (Employees) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return employee;
    }

    public List<Projects> listOfProjects(Integer id) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String hql = "SELECT Employees_and_Projects.projectId, Projects.project_name, start_date, end_date, position_in_project FROM Employees_and_Projects JOIN Projects ON Projects.projectId = Employees_and_Projects.projectId JOIN Employees ON Employees_and_Projects.employeeId = Employees.employeeId WHERE Employees.employeeId = :id";
        Query query = session.createSQLQuery(hql).addEntity(Projects.class);
        query.setParameter("id", id);
        List<Projects> projects = (List<Projects>) query.list();
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return projects;
    }

    public List<Payments> historyOfPayments(Integer id) {
        SessionUI sessionUI = new SessionUI();
        Session session = createSessionFactory().openSession();
        session.beginTransaction();
        String hql = "FROM Payments";
        Query query = session.createQuery(hql);
        List<Payments> res = (List<Payments>) query.list();
        session.getTransaction().commit();
        session.close();
        sessionUI.getSessionFactory().close();
        return res;
    }
}
