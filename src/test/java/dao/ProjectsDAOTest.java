package dao;

import entity.Employees;
import entity.Projects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

public class ProjectsDAOTest {
    @Test
    void insert() throws Exception{
        Projects projects = new Projects();
        projects.setProject_name("new project");
        projects.setStart_date(Date.valueOf("2000-08-09"));
        projects.setEnd_date(Date.valueOf("2001-08-01"));
        ProjectsDAO projectsDAO = new ProjectsDAO();
        Assertions.assertEquals(0, projectsDAO.insert(projects));
    }

    @org.junit.jupiter.api.Test
    void remove() throws Exception{
        Projects projects = new Projects();
        ProjectsDAO projectsDAO = new ProjectsDAO();
        projects = projectsDAO.listOfProjects().get(1);
        Assertions.assertEquals(0, projectsDAO.remove(projects));
    }

    @org.junit.jupiter.api.Test
    void update() throws Exception{
        Projects projects = new Projects();
        ProjectsDAO projectsDAO = new ProjectsDAO();
        projects = projectsDAO.listOfProjects().get(0);
        projects.setEnd_date(Date.valueOf("2000-08-08"));
        Assertions.assertEquals(0, projectsDAO.update(projects));
    }

    @Test
    void infoAboutProject() throws Exception {
        Projects projects = new Projects();
        ProjectsDAO projectsDAO = new ProjectsDAO();
        projects = projectsDAO.listOfProjects().get(0);
        Assertions.assertEquals("new project", projectsDAO.infoAboutProject(projects.getId()).getProject_name());
    }

    @org.junit.jupiter.api.Test
    void listOfProjects() throws Exception{
        ProjectsDAO projectsDAO = new ProjectsDAO();
        Assertions.assertEquals(1,projectsDAO.listOfProjects().get(0).getId());
    }

    @Test
    void listOfEmployeesInProject() throws Exception{
        Projects projects = new Projects();
        ProjectsDAO projectsDAO = new ProjectsDAO();
        projects = projectsDAO.listOfProjects().get(0);
        Assertions.assertEquals("Vassiliy Pupkin", projectsDAO.listOfEmployeesInProject(projects.getId()).get(0).getFull_name());
    }

    @Test
    void listOfPositionInProject() throws Exception {
        Projects projects = new Projects();
        ProjectsDAO projectsDAO = new ProjectsDAO();
        projects = projectsDAO.listOfProjects().get(0);
        Assertions.assertEquals("qwe", projectsDAO.listOfPositionInProject(projects.getId()).get(0).getPosition());
    }
}
