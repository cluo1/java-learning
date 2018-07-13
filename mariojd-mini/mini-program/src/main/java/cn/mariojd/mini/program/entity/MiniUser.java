package cn.mariojd.mini.program.entity;

import lombok.Data;

@Data
public class MiniUser {

    /**
     * id
     */
    private Integer id;
    /**
     * 微信名
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 小程序openId
     */
    private String openId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 学生学号
     */
    private String studentId;
    /**
     * 身份。1：老师，2：班干部，3：普通学生
     */
    private Integer role;
    /**
     * 班级id。老师可能有多个
     */
    private String cids;

}
