package com.stylefeng.guns.modular.mini.vo;


import java.util.List;

public class ClassesAndUserInfoVO {

    /**
     * 班级id，老师可能有多个
     */
    private List<Integer> ids;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 班级id，学生只有一个
     */
    private Integer id;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 性别
     */
    private Integer gender;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
