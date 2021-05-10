package com.example.application;
import dao.EmployeesDAO;
import dao.Employees_and_ProjectsDAO;
import dao.PaymentsDAO;
import dao.ProjectsDAO;
import entity.Employees;
import entity.Employees_and_Projects;
import entity.Payments;
import entity.Projects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
@org.springframework.stereotype.Controller
public class GreetingController {
    @GetMapping
    public String start() {
        return "start";
    }

    @GetMapping("/Employees")
    public String main(Map<String, Object> model) {
        EmployeesDAO employeesDAO = new EmployeesDAO();
        List<Employees> employees = employeesDAO.listOfEmployees();
        model.put("employees", employees);
        return "employees";
    }

    @PostMapping("/Employees")
    public String criteria(@RequestParam(required = false) String work_experience, @RequestParam(required = false) String position, @RequestParam(required = false) String project_name ,Map<String, Object> model) {
        EmployeesDAO employeesDAO = new EmployeesDAO();
        List<Employees> employees = null;
        if (work_experience != null)
            employees = employeesDAO.getByWorkExperience(work_experience);
        else if (position != null)
            employees = employeesDAO.getByPosition(position);
        else if (project_name != null)
            employees = employeesDAO.getByProjectName(project_name);
        model.put("employees", employees);
        return "list";
    }
    @GetMapping("/Employees/add")
    public String add() {
        return "add";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public  String change (@PathVariable String id, @RequestParam(required = false) String full_name, @RequestParam(required = false) String end_work, @RequestParam(required = false) String project_name, @RequestParam(required = false) String start_work, @RequestParam(required = false) String position, @RequestParam(required = false) String home_address, @RequestParam(required = false) String work_experience, @RequestParam(required = false) String education, @RequestParam(required = false) String date_of_birth, Map<String, Object> model) {
        Employees employees = new Employees();
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employees = employeesDAO.getById(Integer.parseInt(id));
        Employees_and_ProjectsDAO employees_and_projectsDAO = new Employees_and_ProjectsDAO();
        Employees_and_Projects employees_and_projects = new Employees_and_Projects();
        Projects projects = new Projects();
        if (full_name == null && work_experience == null && education == null && education == null && home_address == null && date_of_birth == null && position == null && start_work == null && end_work == null && project_name == null) {
            employeesDAO.remove(employees);
            return "delete";
        }
        if (!full_name.isEmpty())
            employees.setFull_name(full_name);
        if (!work_experience.isEmpty())
            employees.setWork_experience(work_experience);
        if (education != null)
            employees.setEducation(education);
        if (home_address !=null)
            employees.setHome_address(home_address);
        if(!date_of_birth.isEmpty())
            employees.setDate_of_birth(Date.valueOf(date_of_birth));
        employees_and_projects.setEmployee(employees);
        if (position != null)
            employees_and_projects.setPosition(position);
        if (!start_work.isEmpty())
            employees_and_projects.setStart_work(Date.valueOf(start_work));
        if (!end_work.isEmpty())
            employees_and_projects.setEnd_work(Date.valueOf(end_work));
        if (project_name != null)
            projects.setProject_name(project_name);
        employeesDAO.update(employees);
        return "delete";
    }

    @GetMapping("/Employees/{id}/history")
    public String historyGet(@PathVariable String id, Map<String, Object> model) {
        Employees employee;
        EmployeesDAO employeesDAO = new EmployeesDAO();
        PaymentsDAO paymentsDAO = new PaymentsDAO();
        employee = employeesDAO.getById(Integer.parseInt(id));
        Payments payments = new Payments();
        payments = paymentsDAO.getByEmployee(employee.getId());
        model.put("payments", payments);
        model.put("employee", employee);
        Employees_and_ProjectsDAO employees_and_projectsDAO = new Employees_and_ProjectsDAO();
        Employees_and_Projects employees_and_projects = new Employees_and_Projects();
        employees_and_projects = employees_and_projectsDAO.getByEmployee(employee.getId());
        model.put("emp_and_pr", employees_and_projects);
        return "history";
    }
    @PostMapping("/Employees/{id}/history")
    public String history() {
        return "history";
    }

    @PostMapping("/Employees/add")
    public  String add (@RequestParam String position, @RequestParam String end_work, @RequestParam String project_name, @RequestParam String start_work, @RequestParam String full_name, @RequestParam String home_address, @RequestParam String work_experience, @RequestParam String education, @RequestParam(defaultValue = "2000-01-01") String date_of_birth, Map<String, Object> model) {
        Employees employee = new Employees();
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employee.setEducation(education);
        employee.setWork_experience(work_experience);
        employee.setHome_address(home_address);
        employee.setFull_name(full_name);
        employee.setDate_of_birth(Date.valueOf(date_of_birth));
        employeesDAO.insert(employee);
        ProjectsDAO projectsDAO = new ProjectsDAO();
        Projects projects = projectsDAO.getByProjectName(project_name);
        Employees_and_Projects employees_and_projects = new Employees_and_Projects();
        Employees_and_ProjectsDAO employees_and_projectsDAO = new Employees_and_ProjectsDAO();
        employees_and_projects.setStart_work(Date.valueOf(start_work));
        employees_and_projects.setEnd_work(Date.valueOf(end_work));
        employees_and_projects.setEmployee(employee);
        employees_and_projects.setPosition(position);
        employees_and_projects.setProject(projects);
        employees_and_projectsDAO.insert(employees_and_projects);
        return "add";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String info(@PathVariable String id, Map<String, Object> model) {
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Employees employee = employeesDAO.infoAboutEmployee(Integer.parseInt(id));
        model.put("employee", employee);
        Employees_and_ProjectsDAO employees_and_projectsDAO = new Employees_and_ProjectsDAO();
        ProjectsDAO projectsDAO = new ProjectsDAO();
        model.put("emp_and_pr", employees_and_projectsDAO.listOfEmployees_and_Projects());
        model.put("project", projectsDAO.listOfProjects());
        return "info";
    }

    @GetMapping("/Projects")
    public String projects(Map<String, Object> model) {
        ProjectsDAO projectsDAO = new ProjectsDAO();
        List<Projects> projects = projectsDAO.listOfProjects();
        model.put("projects", projects);
        return "Projects";
    }

    @GetMapping("/Projects/add")
    public String addProject() {
        return "addProject";
    }

    @PostMapping("/Projects/add")
    public  String addProject(@RequestParam String project_name, @RequestParam(defaultValue = "2000-01-01") String start_date, @RequestParam(defaultValue = "2000-03-03") String end_date, Map<String, Object> model) {
        Projects projects = new Projects();
        ProjectsDAO projectsDAO = new ProjectsDAO();
        projects.setProject_name(project_name);
        projects.setStart_date(Date.valueOf(start_date));
        projects.setEnd_date(Date.valueOf(end_date));
        projectsDAO.insert(projects);
        return "addProject";
    }

    @RequestMapping(value = "/Projects/{id}", method = RequestMethod.GET)
    public String infoProject(@PathVariable String id, Map<String, Object> model) {
        ProjectsDAO projectsDAO = new ProjectsDAO();
        Projects projects = projectsDAO.infoAboutProject(Integer.parseInt(id));
        model.put("projects", projects);
        List<Employees_and_Projects> positions = projectsDAO.listOfPositionInProject(Integer.parseInt(id));
        model.put("positions", positions);
        model.put("emp_and_pr", projectsDAO.listOfEmployeesInProject(Integer.parseInt(id)));
        return "infoProject";
    }
    @RequestMapping(value = "/Projects/{id}", method = RequestMethod.POST)
    public  String changeProject(@PathVariable String id, @RequestParam(required = false) String project_name, @RequestParam(required = false) String start_date, @RequestParam(required = false) String end_date, Map<String, Object> model) {
        ProjectsDAO projectsDAO = new ProjectsDAO();
        Projects projects = projectsDAO.getById(Integer.parseInt(id));
        if (project_name == null && start_date == null && end_date == null) {
            projectsDAO.remove(projects);
            return "deleteProject";
        }
        if (!project_name.isEmpty())
            projects.setProject_name(project_name);
        if (!start_date.isEmpty())
            projects.setStart_date(Date.valueOf(start_date));
        if (!end_date.isEmpty())
            projects.setEnd_date(Date.valueOf(end_date));
        projectsDAO.update(projects);
        return "deleteProject";
    }

    @GetMapping("/Payments")
    public String Payments(Map<String, Object> model) {
        PaymentsDAO paymentsDAO = new PaymentsDAO();
        List<Payments> payments = paymentsDAO.listOfPayments();
        EmployeesDAO employeesDAO = new EmployeesDAO();
        model.put("payments", payments);
        for (int i = 0; i < payments.size(); i++) {
            Employees employees = payments.get(i).getEmployee();
            model.put("employees", employees);
        }
        return "payments";
    }

    @GetMapping("/Payments/add")
    public String addPayment() {
        return "addPayment";
    }

    @PostMapping("/Payments/add")
    public  String addPayment(@RequestParam String money,@RequestParam String full_name, @RequestParam String position,  @RequestParam String work_experience, @RequestParam String project_name, @RequestParam(required = false) String special_date,  Map<String, Object> model) {
        Payments payments = new Payments();
        PaymentsDAO paymentsDAO = new PaymentsDAO();
        EmployeesDAO employeesDAO = new EmployeesDAO();
        ProjectsDAO projectsDAO = new ProjectsDAO();
        payments.setMoney(Integer.parseInt(money));
        payments.setPosition(position);
        if (!special_date.isEmpty())
            payments.setSpecial_date(Date.valueOf(special_date));
        else
            payments.setSpecial_date(null);
        payments.setWork_experience(work_experience);
        payments.setProject_name(project_name);
        payments.setProject(projectsDAO.getByProjectName(project_name));
        payments.setEmployee(employeesDAO.getByFullName(full_name));
        paymentsDAO.insert(payments);
        return "addPayment";
    }

    @RequestMapping(value = "/Payments/change/{id}", method = RequestMethod.GET)
    public String changePayment() {
        return "stick";
    }

    @RequestMapping(value = "/Payments/change/{id}", method = RequestMethod.POST)
    public  String changePayment(@PathVariable String id ,@RequestParam String money, @RequestParam(required = false) String position,  @RequestParam(required = false) String work_experience, @RequestParam(required = false) String project_name, @RequestParam(required = false) String special_date,  Map<String, Object> model) {
        PaymentsDAO paymentsDAO = new PaymentsDAO();
        Payments payments = paymentsDAO.getById(Integer.parseInt(id));
        if (!money.isEmpty())
            payments.setMoney(Integer.parseInt(money));
        if (!position.isEmpty())
            payments.setPosition(position);
        if (!work_experience.isEmpty())
            payments.setWork_experience(work_experience);
        if (!project_name.isEmpty())
            payments.setProject_name(project_name);
        if (!special_date.isEmpty())
            payments.setSpecial_date(Date.valueOf(special_date));
        paymentsDAO.update(payments);
        return "changePayment";
    }
}
