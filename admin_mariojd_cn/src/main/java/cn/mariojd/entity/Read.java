package cn.mariojd.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Mario
 */
@Entity
@Table(name = "tb_read")
public class Read {

    @Id
    @GeneratedValue
    private Integer rid;

    private String title;

    private String secondTitle;

    private String author;

    private String url;

    private String review;

    @JoinColumn(name = "aid", foreignKey = @ForeignKey(name = "none"))
    @ManyToOne(targetEntity = Admin.class)
    private Admin admin;

    @Column(name = "post_time", columnDefinition = "TIMESTAMP")
    private Date postTime;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Read{" +
                "rid=" + rid +
                ", title='" + title + '\'' +
                ", secondTitle='" + secondTitle + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", review='" + review + '\'' +
                ", admin=" + admin +
                ", postTime=" + postTime +
                '}';
    }
}
