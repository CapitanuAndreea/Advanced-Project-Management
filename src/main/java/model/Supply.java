package model;

import javax.persistence.*;

@Entity
@Table(name = "SUPPLY")
public class Supply extends BaseEntity {
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE_PER_UNIT")
    private double pricePerUnit;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    public Supply() {
    }

    public Supply(String description, int quantity, double pricePerUnit, Project project) {
        this.description = description;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
