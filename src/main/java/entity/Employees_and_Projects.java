package entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Employees_and_Projects")
public class Employees_and_Projects {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId")
    private Employees employee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    private Projects project;

    @Column(name = "position_in_project")
    private String position;

    @Column(name = "start_work")
    private Date start_work;

    @Column(name = "end_work")
    private Date end_work;

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getStart_work() {
        return start_work;
    }

    public void setStart_work(Date start_work) {
        this.start_work = start_work;
    }

    public Date getEnd_work() {
        return end_work;
    }

    public void setEnd_work(Date end_work) {
        this.end_work = end_work;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
