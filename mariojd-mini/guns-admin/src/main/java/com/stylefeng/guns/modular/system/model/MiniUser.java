package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 小程序用户表
 * </p>
 *
 * @author Jared123
 * @since 2018-04-07
 */
@TableName("tb_mini_user")
public class MiniUser extends Model<MiniUser> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 微信名
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 小程序openId
     */
    @TableField("openId")
    private String openId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 真实姓名
     */
    @TableField("realName")
    private String realName;
    /**
     * 学生学号
     */
    @TableField("studentId")
    private String studentId;
    /**
     * 身份。1：老师，2：班干部，3：普通学生
     */
    private Integer role;
    /**
     * 班级id。老师可能有多个
     */
    private String cids;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getCids() {
        return cids;
    }

    public void setCids(String cids) {
        this.cids = cids;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MiniUser{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", openId='" + openId + '\'' +
                ", phone='" + phone + '\'' +
                ", realName='" + realName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", role=" + role +
                ", cids='" + cids + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
