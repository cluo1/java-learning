package cn.mariojd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.Date;

/**
 * Created by Mario
 */
@Entity
@Table(name = "tb_notice")
public class Notice {

    @Id
    @GeneratedValue
    private Integer nid;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "post_time", columnDefinition = "TIMESTAMP")
    private Date postTime;

    @JoinColumn(name = "aid", foreignKey = @ForeignKey(name = "none"))
    @ManyToOne(targetEntity = Admin.class)
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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
