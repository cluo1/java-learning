package com.stylefeng.guns.modular.mini.vo;

import java.util.Date;

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
    private Date startTime;
    /**
     * 上课结束时间
     */
    private Date endTime;
    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 是否开始考勤
     */
    private Boolean attendance = false;

    /**
     * 是否考勤中
     */
    private Boolean attendancing = false;

    /**
     * 考勤中id
     */
    private Integer attendanceId;

    /**
     * 考勤中的考勤方式
     */
    private Integer type;

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getAttendancing() {
        return attendancing;
    }

    public void setAttendancing(Boolean attendancing) {
        this.attendancing = attendancing;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}
