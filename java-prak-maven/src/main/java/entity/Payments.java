package entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Payments")
public class Payments {


    @Id
    @Column(name = "paymentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "money")
    private Integer money;

    @Column(name = "special_date")
    private Date special_date;

    @Column(name = "position")
    private String position;

    @ManyToOne(targetEntity=Employees.class)
    @JoinColumn (name="employeeId")
    private Employees employee;

    @ManyToOne(targetEntity=Projects.class)
    @JoinColumn (name="projectId")
    private Projects project;

    public Employees getEmployee() {
        return employee;
    }

    public Projects getProject() {
        return project;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Date getSpecial_date() {
        return special_date;
    }

    public void setSpecial_date(Date special_date) {
        this.special_date = special_date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public void setProject(Projects project) {
        this.project = project;
    }
}
