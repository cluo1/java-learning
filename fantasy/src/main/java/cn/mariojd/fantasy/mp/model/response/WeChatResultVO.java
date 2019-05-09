package cn.mariojd.fantasy.mp.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jared
 * @date 2018/11/23 9:53
 */
@Data
public class WeChatResultVO implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 公众号名称
     */
    private String mpsName;

    /**
     * 公众号描述
     */
    private String desc;

    /**
     * 公众号Id
     */
    private String weChatId;

    /**
     * 公众号头像
     */
    private String logoImg;

}
