package cn.mariojd.fantasy.mp.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Jared
 * @date 2018/11/23 9:47
 */
@Data
@Entity
@Table(name = "tb_mps")
public class WeChatMp {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公众号名称
     */
    @Column(name = "mps_name")
    private String mpsName;

    /**
     * 公众号标志
     */
    @Column(name = "mps_biz")
    private String mpsBiz;

    /**
     * 公众号描述
     */
    private String desc;

    /**
     * 公众号Id
     */
    @Column(name = "wechat_id")
    private String weChatId;

    /**
     * 公众号头像
     */
    @Column(name = "logo_img")
    private String logoImg;

    /**
     * 是否展示
     */
    private Boolean show;

}
