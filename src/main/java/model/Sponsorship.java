package model;

import javax.persistence.*;

@Entity
@Table(name = "SPONSORSHIP")
public class Sponsorship extends BaseEntity {
    @Column(name = "AMOUNT_OFFERED")
    private double amountOffered;

    @ManyToOne
    @JoinColumn(name = "SPONSOR_ID")
    private Sponsor sponsor;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    public Sponsorship() {
    }

    public Sponsorship(double amountOffered, Sponsor sponsor, Project project) {
        this.amountOffered = amountOffered;
        this.sponsor = sponsor;
        this.project = project;
    }

    public double getAmountOffered() {
        return amountOffered;
    }

    public void setAmountOffered(double amountOffered) {
        this.amountOffered = amountOffered;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
