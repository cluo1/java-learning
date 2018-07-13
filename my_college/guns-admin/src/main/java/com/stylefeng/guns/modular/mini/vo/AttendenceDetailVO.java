package com.stylefeng.guns.modular.mini.vo;

import java.util.Date;

public class AttendenceDetailVO {

    private Integer id;
    /**
     * 考勤外键id
     */
    private Integer aid;
    /**
     * 学生id
     */
    private Integer sid;
    /**
     * 是否已考勤
     */
    private Boolean punch;
    /**
     * 考勤时间
     */
    private Date punchTime;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学号
     */
    private String studentNum;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 身份
     */
    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Boolean getPunch() {
        return punch;
    }

    public void setPunch(Boolean punch) {
        this.punch = punch;
    }

    public Date getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Date punchTime) {
        this.punchTime = punchTime;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }
}
