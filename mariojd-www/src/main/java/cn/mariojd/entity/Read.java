package cn.mariojd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mario
 */
public class Read implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer rid;
    private String title;
    private String secondTitle;
    private String author;
    private String url;
    private String review;
    private Admin admin;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm", timezone = "GMT+8")
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
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
