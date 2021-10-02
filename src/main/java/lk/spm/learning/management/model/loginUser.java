package lk.spm.learning.management.model;
import javax.persistence.*;

@Entity
@Table(name = "LoginUsers")
public class loginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;

    @Column(name = "password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
