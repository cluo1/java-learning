package cn.mariojd.mini.program.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Classes {

    /**
     * id
     */
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
    private String simpleName;
    /**
     * 班级全称
     */
    private String fullName;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createtime;

}
