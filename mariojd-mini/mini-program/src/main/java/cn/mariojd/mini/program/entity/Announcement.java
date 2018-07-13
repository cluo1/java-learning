package cn.mariojd.mini.program.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Announcement {

    /**
     * id
     */
    private Integer id;
    /**
     * 班级id
     */
    private Integer cid;
    /**
     * 提交人id
     */
    private Integer uid;
    /**
     * 发布人
     */
    private String publisher;
    /**
     * 排序
     */
    private Integer num;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createtime;

}
