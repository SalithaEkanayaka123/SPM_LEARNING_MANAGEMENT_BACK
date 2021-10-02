package lk.spm.learning.management.model;

import javax.persistence.*;

@Entity
@Table(name = "videos")
public class LectureVideos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "image")
    private String image;

    public LectureVideos(){

    }

    public LectureVideos(long id, String name, String url, String image) {
        this.id = id;
        this.name = name;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
