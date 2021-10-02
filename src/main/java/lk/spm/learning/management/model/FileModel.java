package lk.spm.learning.management.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="files")
public class FileModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String uri;
    private String type;
    private long size;
    private String price;
    private String description;
    private String course;
    private String userGivenName;
    @Lob
    private byte[] data;

    public FileModel(){

    }

    public FileModel(String name, String uri, String type) {
        this.name = name;
        this.uri = uri;
        this.type = type;
    }

    public FileModel(String name, String uri, String type, String price, String description, String course, String userGivenName) {
        this.name = name;
        this.uri = uri;
        this.type = type;
        this.price = price;
        this.description = description;
        this.course = course;
        this.userGivenName = userGivenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUserGivenName() {
        return userGivenName;
    }

    public void setUserGivenName(String userGivenName) {
        this.userGivenName = userGivenName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
