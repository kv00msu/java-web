package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Employees")
public class Employees {

    @Id
    @Column(name = "employeeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "home_address")
    private String home_address;

    @Column(name = "date_of_birth")
    private Date date_of_birth;

    @Column(name = "education")
    private String education;

    @Column(name = "work_experience")
    private String work_experience;

    @OneToMany (mappedBy = "employee")
    private List<Payments> payments;

    @OneToMany (mappedBy = "employee")
    private List<Employees_and_Projects> emp_and_pr;

    public Employees() {
    }

    public Integer getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getHome_address() {
        return home_address;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public String getEducation() {
        return education;
    }

    public String getWork_experience() {
        return work_experience;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setWork_experience(String work_experience) {
        this.work_experience = work_experience;
    }
}


