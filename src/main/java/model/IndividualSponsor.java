package model;

import javax.persistence.*;

@Entity
@Table(name = "INDIVIDUAL_SPONSOR")
public class IndividualSponsor extends Sponsor {
    @Column(name = "BADGE")
    private String badge = "Individual";

    public IndividualSponsor() {
        super();
    }

    public IndividualSponsor(String name, String email) {
        super(name, email);
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
}
