package model;

import javax.persistence.*;

@Entity
@Table(name = "ORGANIZATION_SPONSOR")
public class OrganizationSponsor extends Sponsor {
    @Column(name = "BADGE")
    private String badge = "Organization";

    public OrganizationSponsor() {
        super();
    }

    public OrganizationSponsor(String name, String email) {
        super(name, email);
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
}
