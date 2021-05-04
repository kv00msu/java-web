package selenium;

import dao.EmployeesDAO;
import dao.Employees_and_ProjectsDAO;
import dao.PaymentsDAO;
import dao.ProjectsDAO;
import entity.Employees;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Date;
import java.util.List;

public class MyControllerTest {
    private ChromeDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/vasya/Downloads/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void End() {
        driver.quit();
    }

    @Test
    public void start() {
        driver.get("http://localhost:8080/");
        Assertions.assertEquals("http://localhost:8080/",driver.getCurrentUrl());
    }
    @Test
    public void main() {
        driver.get("http://localhost:8080/Employees");
        Assertions.assertEquals("1Vassiliy Kozhemyak", driver.findElementByLinkText("1Vassiliy Kozhemyak").getText());
    }
    @Test
    public void add() {
        Employees employees = new Employees();
        EmployeesDAO employeesDAO = new EmployeesDAO();
        List<Employees> listEmployees = employeesDAO.listOfEmployees();
        Assertions.assertEquals("Maken Alfarabi", listEmployees.get(listEmployees.size() - 1).getFull_name());
    }
    @Test
    public void change() {
        Employees employees = new Employees();
        EmployeesDAO employeesDAO = new EmployeesDAO();
        List<Employees> listEmployees = employeesDAO.listOfEmployees();
        employees = listEmployees.get(0);
        Assertions.assertEquals("4 years",employees.getWork_experience());
    }
    @Test
    public void criteria() {
        driver.get("http://localhost:8080/Employees");
        Assertions.assertEquals( "1Vassiliy Kozhemyak", driver.findElementByLinkText("1Vassiliy Kozhemyak").getText());
    }
    @Test
    public void info() {
        driver.get("http://localhost:8080/Employee/1");
        Assertions.assertEquals(false, driver.findElementsByCssSelector("body > div:nth-child(2) > span:nth-child(9) > b:nth-child(1)").isEmpty());
    }
    @Test
    public void historyGet() {
        driver.get("http://localhost:8080/Employees/1/history?");
        Assertions.assertEquals("50000", driver.findElementsByTagName("span").get(5).getText());
    }
    @Test
    public void projects() {
        driver.get("http://localhost:8080/Projects");
        Assertions.assertEquals("1 " + '"' + "clean" + '"', driver.findElementByLinkText("1 " + '"' + "clean" + '"').getText());
    }
    @Test
    public void infoProject() {
        driver.get("http://localhost:8080/Projects/1");
        Assertions.assertEquals("Project name:" + '"' + "clean" + '"', driver.findElementsByTagName("span").get(1).getText());
    }
    @Test
    public void addProject() {
        ProjectsDAO projectsDAO = new ProjectsDAO();
        Assertions.assertEquals("green city", projectsDAO.listOfProjects().get(projectsDAO.listOfProjects().size() - 1).getProject_name());
    }
    @Test
    public void Payments() {
        driver.get("http://localhost:8080/Payments");
        Assertions.assertEquals("30000", driver.findElementsByTagName("span").get(1).getText());
    }
    @Test
    public void addPayment() {
        PaymentsDAO paymentsDAO = new PaymentsDAO();
        Assertions.assertEquals(30000, paymentsDAO.listOfPayments().get(paymentsDAO.listOfPayments().size() - 1).getMoney());
    }
    @Test
    public void changePayment() {
        driver.get("http://localhost:8080/Payments/change/1?");
        Assertions.assertEquals("",driver.findElementByName("money").getText());
    }
    @Test
    public void changeProject() {
        ProjectsDAO projectsDAO = new ProjectsDAO();
        Assertions.assertEquals(Date.valueOf("2020-01-03"), projectsDAO.listOfProjects().get(1).getStart_date());
    }
}