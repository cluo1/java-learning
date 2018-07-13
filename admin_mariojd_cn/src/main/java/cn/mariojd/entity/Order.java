package cn.mariojd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mario
 */
@Entity
@Table(name = "tb_order")
@IdClass(OrderPK.class)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String oid;

    @Id
    private Integer uid;

    @Id
    private Integer sid;

    private String sname;

    private Integer sprice;

    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time", columnDefinition = "TIMESTAMP")
    private Date createTime;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSprice() {
        return sprice;
    }

    public void setSprice(Integer sprice) {
        this.sprice = sprice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", uid=" + uid +
                ", sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sprice=" + sprice +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
