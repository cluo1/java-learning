package com.stylefeng.guns.modular.system.transfer;

import java.util.Date;

/**
 * Course实体的包装类
 */
public class CourseVO {

    private Integer id;
    /**
     * 班级id
     */
    private Integer cid;
    /**
     * 老师id
     */
    private Integer tid;
    /**
     * 提交人id
     */
    private Integer uid;
    /**
     * 排序
     */
    private Integer num;
    /**
     * 课程名
     */
    private String name;
    /**
     * 开始日期
     */
    private Date startDate;
    /**
     * 结束日期
     */
    private Date endDate;
    /**
     * 星期1-7
     */
    private Integer weekDay;
    /**
     * 上课开始时间
     */
    private String startTime;
    /**
     * 上课结束时间
     */
    private String endTime;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "CourseVO{" +
                "id=" + id +
                ", cid=" + cid +
                ", tid=" + tid +
                ", uid=" + uid +
                ", num=" + num +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", weekDay=" + weekDay +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", createtime=" + createtime +
                '}';
    }
}
