package cn.mariojd.mini.program.dto;

import lombok.Data;

/**
 * @author jdq
 * @date 2017/12/15 16:36
 */
@Data
public class WeChatUser {

    /**
     * openId
     */
    private String openId;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户性别
     */
    private Integer gender;

}
