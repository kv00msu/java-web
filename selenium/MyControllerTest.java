package selenium;

import dao.EmployeesDAO;
import dao.Employees_and_ProjectsDAO;
import dao.PaymentsDAO;
import dao.ProjectsDAO;
import entity.Employees;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        driver.get("http://localhost:8080/Employees/add?");
        WebElement button = driver.findElementByXPath("/html/body/div/form[1]/button");
        WebElement full_name = driver.findElementByXPath("/html/body/div/form[1]/input[1]");
        WebElement work_experience = driver.findElementByXPath("/html/body/div/form[1]/input[2]");
        WebElement home_address = driver.findElementByXPath("/html/body/div/form[1]/input[3]");
        WebElement date_of_birth = driver.findElementByXPath("/html/body/div/form[1]/input[4]");
        WebElement education = driver.findElementByXPath("/html/body/div/form[1]/input[5]");
        WebElement position = driver.findElementByXPath("/html/body/div/form[1]/input[6]");
        WebElement start_work = driver.findElementByXPath("/html/body/div/form[1]/input[7]");
        WebElement end_work = driver.findElementByXPath("/html/body/div/form[1]/input[8]");
        WebElement project_name = driver.findElementByXPath("/html/body/div/form[1]/input[9]");
        full_name.sendKeys("Vasya Pupkin");
        work_experience.sendKeys("2 years");
        home_address.sendKeys("Mustafina 4");
        date_of_birth.sendKeys("2002-04-21");
        education.sendKeys("KSU");
        position.sendKeys("director");
        start_work.sendKeys("2017-08-04");
        end_work.sendKeys("2018-04-01");
        project_name.sendKeys("green city");
        button.click();
        driver.get("http://localhost:8080/Employees");
        WebElement employee = driver.findElementByXPath("/html/body/div[7]/a");
        Assertions.assertEquals("14Vasya Pupkin", employee.getText());
    }
    @Test
    public void change() {
        driver.get("http://localhost:8080/Employee/3");
        WebElement button = driver.findElementByXPath("/html/body/div[4]/form[2]/button");
        WebElement home_address = driver.findElementByXPath("/html/body/div[4]/form[2]/input[3]");
        home_address.sendKeys("Nazarbaeva 26");
        button.click();
        driver.get("http://localhost:8080/Employee/3");
        WebElement employee = driver.findElementByXPath("/html/body/div[2]/span[3]/b");
        Assertions.assertEquals("Nazarbaeva 26", employee.getText());
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
        Assertions.assertEquals("85000", driver.findElementsByTagName("span").get(5).getText());
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
    public void Payments() {
        driver.get("http://localhost:8080/Payments");
        Assertions.assertEquals("85000", driver.findElementsByTagName("span").get(1).getText());
    }
    @Test
    public void caddProject() {
        driver.get("http://localhost:8080/Projects/add?");
        WebElement project_name = driver.findElementByXPath("/html/body/div/form[1]/input[1]");
        WebElement start_date = driver.findElementByXPath("/html/body/div/form[1]/input[2]");
        WebElement end_date = driver.findElementByXPath("/html/body/div/form[1]/input[3]");
        WebElement button = driver.findElementByXPath("/html/body/div/form[1]/button");
        project_name.sendKeys("my project");
        start_date.sendKeys("2021-01-02");
        end_date.sendKeys("2021-03-08");
        button.click();
        driver.get("http://localhost:8080/Projects");
        Assertions.assertEquals("my project", driver.findElementByXPath("/html/body/div[4]/a/span").getText());
    }
    @Test
    public void addPayment() {
        driver.get("http://localhost:8080/Payments/add?");
        WebElement button = driver.findElementByXPath("/html/body/div/form[1]/button");
        WebElement money = driver.findElementByXPath("/html/body/div/form[1]/input[1]");
        WebElement position = driver.findElementByXPath("/html/body/div/form[1]/input[2]");
        WebElement work_experience = driver.findElementByXPath("/html/body/div/form[1]/input[3]");
        WebElement project_name = driver.findElementByXPath("/html/body/div/form[1]/input[4]");
        WebElement date_prize = driver.findElementByXPath("/html/body/div/form[1]/input[5]");
        WebElement employee_name = driver.findElementByXPath("/html/body/div/form[1]/input[6]");
        money.sendKeys("25000");
        position.sendKeys("caller");
        work_experience.sendKeys("1 year");
        project_name.sendKeys("my project");
        date_prize.sendKeys("2021-05-09");
        employee_name.sendKeys("Vasya Pupkin");
        button.click();
        driver.get("http://localhost:8080/Payments");
        Assertions.assertEquals("25000", driver.findElementByXPath("/html/body/div[3]/span[1]").getText());
    }
    @Test
    public void changePayment() {
        driver.get("http://localhost:8080/Payments/change/1?");
        WebElement money = driver.findElementByXPath("/html/body/div/form/input[1]");
        WebElement button = driver.findElementByXPath("/html/body/div/form/button");
        money.sendKeys("85000");
        button.click();
        driver.get("http://localhost:8080/Payments");
        Assertions.assertEquals("85000", driver.findElementByXPath("/html/body/div[2]/span[1]").getText());
    }
    @Test
    public void changeProject() {
        driver.get("http://localhost:8080/Projects/2");
        WebElement button = driver.findElementByXPath("/html/body/div[4]/form[2]/button");
        WebElement start_date = driver.findElementByXPath("/html/body/div[4]/form[2]/input[2]");
        start_date.sendKeys("2020-01-02");
        button.click();
        driver.get("http://localhost:8080/Projects/2");
        WebElement project = driver.findElementByXPath("/html/body/div[2]/span[3]/b");
        Assertions.assertEquals("2020-01-02", project.getText());
    }

}
