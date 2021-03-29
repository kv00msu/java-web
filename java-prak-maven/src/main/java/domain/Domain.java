package domain;

import dao.EmployeesDAO;
import dao.Employees_and_ProjectsDAO;
import dao.PaymentsDAO;
import dao.ProjectsDAO;
import entity.Employees;
import entity.Employees_and_Projects;
import entity.Payments;
import entity.Projects;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Domain {
    public static void main(String[] args) throws Exception {
  /*      Employees employee = new Employees();
        employee.setId(101);
        employee.setEducation("МГУ");
        employee.setFull_name("Vassiliy Pupkin");
        employee.setDate_of_birth(Date.valueOf("2000-01-01"));
        employee.setWork_experience("1 year");
        employee.setHome_address("Сверловская,5");
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employeesDAO.insert(employee);

        Projects project = new Projects();
        project.setProject_name("Влияние газированных напитков на ЖКТ человек");
        project.setStart_date(Date.valueOf("2000-08-09"));
        project.setEnd_date(Date.valueOf("2001-11-15"));
        ProjectsDAO projectsDAO = new ProjectsDAO();
        projectsDAO.insert(project);

        Employees_and_ProjectsDAO employees_and_projectsDAO = new Employees_and_ProjectsDAO();
        Employees_and_Projects employees_and_projects = new Employees_and_Projects();
        employees_and_projects.setEmployee(employee);
        employees_and_projects.setProject(project);
        employees_and_projects.setPosition("asd");
        employees_and_projects.setStart_work(Date.valueOf("2000-11-11"));
        employees_and_projects.setEnd_work(Date.valueOf("2001-11-11"));
        employees_and_projectsDAO.insert(employees_and_projects);
*/
        List<Employees_and_Projects> list = new ArrayList();
        ProjectsDAO projectsDAO = new ProjectsDAO();
        list = (List<Employees_and_Projects>) projectsDAO.listOfPositionInProject(1);
        System.out.println(list.get(0).getPosition());

    }
}

