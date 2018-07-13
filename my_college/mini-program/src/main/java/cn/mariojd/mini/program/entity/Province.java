package cn.mariojd.mini.program.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Province {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 排序
     */
    private Integer num;
    /**
     * 省份名称
     */
    private String name;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createtime;

}
