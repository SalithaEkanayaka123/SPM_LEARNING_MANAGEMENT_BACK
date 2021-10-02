package lk.spm.learning.management.model;
import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "date")
    private String date;

    @Column(name = "amount")
    private String amount;

    @Column(name = "type")
    private String type;

    @Column(name = "doneby")
    private String doneby;

    @Column(name = "description")
    private String description;

    @Column(name = "courseid")
    private String courseid;

    public String getCourse_id() {
        return courseid;
    }

    public void setCourse_id(String courseid) {
        this.courseid = courseid;
    }

    public long getTid() {
        return id;
    }

    public void setTid(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDone_by() {
        return doneby;
    }

    public void setDone_by(String doneby) {
        this.doneby = doneby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
