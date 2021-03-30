package dao;

import entity.Employees;
import entity.Payments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

public class PaymentsDAOTest {
    @Test
    void insert() throws Exception{
        EmployeesDAO employeesDAO = new EmployeesDAO();
        ProjectsDAO projectsDAO = new ProjectsDAO();
        Payments payment = new Payments();
        payment.setSpecial_date(Date.valueOf("2000-01-01"));
        payment.setProject(projectsDAO.listOfProjects().get(0));
        payment.setMoney(100000);
        payment.setPosition("уборщик");
        payment.setEmployee(employeesDAO.listOfEmployees().get(0));
        PaymentsDAO paymentsDAO = new PaymentsDAO();
        Assertions.assertEquals(0, paymentsDAO.insert(payment));
    }

    @org.junit.jupiter.api.Test
    void remove() throws Exception{
        Payments payment = null;
        PaymentsDAO paymentsDAO = new PaymentsDAO();
        payment = paymentsDAO.listOfPayments().get(1);
        Assertions.assertEquals(0, paymentsDAO.remove(payment));
    }

    @org.junit.jupiter.api.Test
    void update() throws Exception{
        Payments payment = null;
        PaymentsDAO paymentsDAO = new PaymentsDAO();
        payment = paymentsDAO.listOfPayments().get(0);
        payment.setMoney(170000);
        Assertions.assertEquals(0, paymentsDAO.update(payment));
    }

    @org.junit.jupiter.api.Test
    void listOfPayments() throws Exception{
        PaymentsDAO paymentsDAO = new PaymentsDAO();
        Assertions.assertEquals(5,paymentsDAO.listOfPayments().get(0).getId());
    }
}
