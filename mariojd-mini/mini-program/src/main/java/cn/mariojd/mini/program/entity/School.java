package cn.mariojd.mini.program.entity;

import lombok.Data;

import java.util.Date;

@Data
public class School {

    private Integer id;
    /**
     * 父省份id
     */
    private Integer pid;
    /**
     * 排序
     */
    private Integer num;
    /**
     * 学校简称
     */
    private String simpleName;
    /**
     * 学校全称
     */
    private String fullName;
    /**
     * 学校位置
     */
    private String location;
    /**
     * 学校电话
     */
    private String phone;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createtime;

}
