package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TASK")
public class Task extends BaseEntity {
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DEADLINE")
    private Date deadline;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    public Task() {
    }

    public Task(String description, Date deadline, String status, Project project, Employee employee) {
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.project = project;
        this.employee = employee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
