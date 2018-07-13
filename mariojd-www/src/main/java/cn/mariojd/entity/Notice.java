package cn.mariojd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mario
 */
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer nid;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date postTime;
    private Admin admin;
    private Integer visit;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "nid=" + nid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", postTime=" + postTime +
                ", admin=" + admin +
                ", visit=" + visit +
                '}';
    }
}
