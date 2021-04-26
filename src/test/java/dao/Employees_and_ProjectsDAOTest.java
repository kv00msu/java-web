package dao;

import entity.Employees;
import entity.Employees_and_Projects;
import entity.Payments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

public class Employees_and_ProjectsDAOTest {

    @Test
    void insert() throws Exception{
        Employees_and_Projects employees_and_projects = new Employees_and_Projects();
        EmployeesDAO employeesDAO = new EmployeesDAO();
        ProjectsDAO projectsDAO = new ProjectsDAO();
        employees_and_projects.setProject(projectsDAO.listOfProjects().get(0));
        employees_and_projects.setEmployee(employeesDAO.listOfEmployees().get(0));
        employees_and_projects.setPosition("asd");
        employees_and_projects.setStart_work(Date.valueOf("1999-01-02"));
        employees_and_projects.setEnd_work(Date.valueOf("2000-01-02"));
        Employees_and_ProjectsDAO employees_and_projectsDAO = new Employees_and_ProjectsDAO();
        Assertions.assertEquals(0, employees_and_projectsDAO.insert(employees_and_projects));
    }

    @org.junit.jupiter.api.Test
    void remove() throws Exception{
        Employees_and_Projects employees_and_projects = null;
        Employees_and_ProjectsDAO employees_and_projectsDAO = new Employees_and_ProjectsDAO();
        employees_and_projects = employees_and_projectsDAO.listOfEmployees_and_Projects().get(0);
        Assertions.assertEquals(0, employees_and_projectsDAO.remove(employees_and_projects));
    }

    @org.junit.jupiter.api.Test
    void update() throws Exception{
        Employees_and_Projects employees_and_projects = null;
        Employees_and_ProjectsDAO employees_and_projectsDAO = new Employees_and_ProjectsDAO();
        employees_and_projects = employees_and_projectsDAO.listOfEmployees_and_Projects().get(0);
        employees_and_projects.setPosition("qwe");
        Assertions.assertEquals(0, employees_and_projectsDAO.update(employees_and_projects));
    }

    @org.junit.jupiter.api.Test
    void listOfEmployees_and_Projects() throws Exception{
        Employees_and_ProjectsDAO employees_and_projectsDAO = new Employees_and_ProjectsDAO();
        Assertions.assertEquals(6,employees_and_projectsDAO.listOfEmployees_and_Projects().get(0).getId());
    }

}
