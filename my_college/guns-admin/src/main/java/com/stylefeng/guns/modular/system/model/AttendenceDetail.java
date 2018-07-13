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
 * 考勤明细表
 * </p>
 *
 * @author Jared123
 * @since 2018-05-10
 */
@TableName("tb_attendence_detail")
public class AttendenceDetail extends Model<AttendenceDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
    @TableField("punch_time")
    private Date punchTime;


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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AttendenceDetail{" +
        "id=" + id +
        ", aid=" + aid +
        ", sid=" + sid +
        ", punch=" + punch +
        ", punchTime=" + punchTime +
        "}";
    }
}
