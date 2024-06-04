package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PROJECT")
public class Project extends BaseEntity {
    @Column(name = "TITLE")
    private String title;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DATE")
    private Date date;

    public Project() {
    }

    public Project(String title, String type, Date date) {
        this.title = title;
        this.type = type;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
