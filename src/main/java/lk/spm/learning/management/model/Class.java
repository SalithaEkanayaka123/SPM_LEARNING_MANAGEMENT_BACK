package lk.spm.learning.management.model;
import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "tutorName")
    private String tutorName;

    @Column(name = "image")
    private String image;

    public Class() {

    }

    public Class(String name, String description, String tutorName, String image) {
        this.name = name;
        this.description = description;
        this.tutorName = tutorName;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", name=" + name + ", desc=" + description + ", tutorName=" + tutorName + "]";
    }



}
