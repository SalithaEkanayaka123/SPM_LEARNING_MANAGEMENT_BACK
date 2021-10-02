package lk.spm.learning.management.model;

import javax.persistence.*;

@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "header")
    private String header;

    @Column(name = "body")
    private String body;

    @Column(name = "name")
    private String name;

    public Announcement() {

    }

    public Announcement(long id, String header, String body, String name) {
        this.id = id;
        this.header = header;
        this.body = body;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "Tutorial [id=" + id + ", name=" + name + ", desc=" + description + ", tutorName=" + tutorName + "]";
//    }
}
