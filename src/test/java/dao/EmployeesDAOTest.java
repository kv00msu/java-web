package dao;

import entity.Employees;
import dao.EmployeesDAO;
import entity.Projects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesDAOTest {
    @Test
    void insert() throws Exception{
        Employees employee = new Employees();
        employee.setEducation("МГУ");
        employee.setFull_name("Vassiliy Pupkin");
        employee.setDate_of_birth(Date.valueOf("2000-01-01"));
        employee.setWork_experience("1 year");
        employee.setHome_address("Сверловская,5");
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Assertions.assertEquals(0, employeesDAO.insert(employee));
    }

    @org.junit.jupiter.api.Test
    void remove() throws Exception{
        Employees employee = null;
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employee = employeesDAO.listOfEmployees().get(1);
        Assertions.assertEquals(0, employeesDAO.remove(employee));
    }

    @org.junit.jupiter.api.Test
    void update() throws Exception{
        Employees employee = null;
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employee = employeesDAO.listOfEmployees().get(0);
        employee.setEducation("MSU");
        Assertions.assertEquals(0, employeesDAO.update(employee));
    }

    @org.junit.jupiter.api.Test
    void getByPosition() throws Exception{
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Assertions.assertEquals("Vassiliy Pupkin", employeesDAO.getByPosition("asd").get(0).getFull_name());
    }

    @org.junit.jupiter.api.Test
    void getByWorkExperience() throws Exception{
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Assertions.assertEquals("Vassiliy Pupkin", employeesDAO.getByWorkExperience("1 year").get(0).getFull_name());
    }

    @org.junit.jupiter.api.Test
    void getByProjectName() throws Exception{
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Assertions.assertEquals("Vassiliy Pupkin", employeesDAO.getByProjectName("new project").get(0).getFull_name());
    }

    @org.junit.jupiter.api.Test
    void listOfEmployees() throws Exception{
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Assertions.assertEquals(1,employeesDAO.listOfEmployees().get(0).getId());
    }

    @Test
    void infoAboutEmployee() throws Exception {
        Employees employees = new Employees();
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employees = employeesDAO.listOfEmployees().get(0);
        Assertions.assertEquals("Vassiliy Pupkin", employeesDAO.infoAboutEmployee(employees.getId()).getFull_name());
    }

    @org.junit.jupiter.api.Test
    void listOfProject() throws Exception{
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Assertions.assertEquals("new project", employeesDAO.listOfProjects(1).get(0).getProject_name());
    }

    @org.junit.jupiter.api.Test
    void historyOfPayments() throws Exception{
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Assertions.assertEquals(100000, employeesDAO.historyOfPayments(1).get(0).getMoney());
    }
}