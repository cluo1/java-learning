package cn.mariojd.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mario
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private String oid;
    private Integer uid;
    private Integer sid;
    private String sname;
    private Integer sprice;
    private Integer state;
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
