package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 班级表
 * </p>
 *
 * @author Jared123
 * @since 2018-04-06
 */
@TableName("tb_classes")
public class Classes extends Model<Classes> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父学校id
     */
    private Integer sid;
    /**
     * 排序
     */
    private Integer num;
    /**
     * 班级简称
     */
    @TableField("simple_name")
    private String simpleName;
    /**
     * 班级全称
     */
    @TableField("full_name")
    private String fullName;
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

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Classes{" +
        "id=" + id +
        ", sid=" + sid +
        ", num=" + num +
        ", simpleName=" + simpleName +
        ", fullName=" + fullName +
        ", status=" + status +
        ", createtime=" + createtime +
        "}";
    }
}
