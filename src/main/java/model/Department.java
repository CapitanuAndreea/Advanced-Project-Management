package model;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")
public class Department extends BaseEntity {
    @Column(name = "NAME")
    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
