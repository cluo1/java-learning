package cn.mariojd.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mario
 */
public class Seckill implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer sid;
    private String sname;
    private Integer snumber;
    private Integer sprice;
    private String scategory;
    private String surl;
    private Date startTime;
    private Date endTime;
    private Date createTime;

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

    public Integer getSnumber() {
        return snumber;
    }

    public void setSnumber(Integer snumber) {
        this.snumber = snumber;
    }

    public Integer getSprice() {
        return sprice;
    }

    public void setSprice(Integer sprice) {
        this.sprice = sprice;
    }

    public String getScategory() {
        return scategory;
    }

    public void setScategory(String scategory) {
        this.scategory = scategory;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", snumber=" + snumber +
                ", sprice=" + sprice +
                ", scategory='" + scategory + '\'' +
                ", surl='" + surl + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}

