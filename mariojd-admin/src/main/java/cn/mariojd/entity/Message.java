package cn.mariojd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mario
 */
@Entity
@Table(name = "tb_message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer mid;

    // 一对多，这是多的一方
    @JoinColumn(name = "uid", foreignKey = @ForeignKey(name = "none"))
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(columnDefinition = "TEXT")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "post_time", columnDefinition = "TIMESTAMP")
    private Date postTime;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Message{" +
                "mid=" + mid +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", postTime=" + postTime +
                '}';
    }
}
