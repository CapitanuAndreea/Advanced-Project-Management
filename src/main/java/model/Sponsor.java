package model;

import javax.persistence.*;

@Entity
@Table(name = "SPONSOR")
@Inheritance(strategy = InheritanceType.JOINED)
public class Sponsor extends BaseEntity {
    @Column(name = "NAME")
    protected String name;

    @Column(name = "EMAIL")
    protected String email;

    public Sponsor() {
    }

    public Sponsor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
