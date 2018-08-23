package cn.mariojd.nearjob.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Jared
 * @date 2018/8/23 14:34
 */
@Data
@Table(name = "table_job")
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    /**
     * 岗位名称
     */
    @Column(unique = true)
    private String name;

    /**
     * Boss岗位标志
     */
    private Integer bossCode;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 对应表名
     */
    private String tableName;

}
