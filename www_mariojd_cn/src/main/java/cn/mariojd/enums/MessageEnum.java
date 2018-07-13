package cn.mariojd.enums;

/**
 * Created by Mario
 * 提示信息的枚举类
 */
public enum MessageEnum {

    /**
     * 表示用户状态的提示信息
     */
    NONEXISTENT_USER_MESSAGE(-1, "新注册"),
    INACTIVE_USER_MESSAGE(0, "二次注册"),
    NORMAL_USER_MESSAGE(1, "已注册"),
    DISABLED_USER_MESSAGE(2, "已禁用"),
    /**
     * 邮箱激活时的提示信息
     */
    EMAIL_UNKNOWN_MESSAGE(10001, "未知操作"),
    EMAIL_SUCCESS_MESSAGE(10002, "邮箱激活成功"),
    EMAIL_ERROO_MESSAGE(10003, "邮箱已经注册"),
    EMAIL_ISDISABLE_MESSAGE(10004, "邮箱已被禁用，请联系管理员"),
    /**
     * 手机注册时的提示信息
     */
    TELEPHONE_ERROR_MESSAGE(20001, "错误的手机号或验证码"),
    TELEPHONE_SUCCESS_MESSAGE(20002, "注册成功，正在跳转登录界面"),
    TELEPHONE_ISREGISTERED_MESSAGE(20003, "手机号已经注册"),
    TELEPHONE_ISDISABLED_MESSAGE(20004, "手机号已被禁用，请联系管理员"),
    /**
     * 发邮件时的邮件主体内容
     */
    EMAIL_ACTIVATE(101, "<h1>您好，你正在进行邮箱激活操作，如果这是你本人的操作（不是请忽略），请点击下方链接进行激活（若点击无效，请复制链接到浏览器）<h1>"),
    EMAIL_VERIFICATION(102, "<h1>您好，你正在进行密码找回操作，如果这是你本人的操作（不是请忽略），请复制下方验证码<h1>"),
    EMAIL_BINDING(103, "<h1>您好，你正在进行邮箱绑定操作，如果这是你本人的操作（不是请忽略），请复制下方验证码<h1>"),
    /**
     * 登录信息提示
     */
    LOGIN_SUCCESS(200, "登录成功"),
    LOGIN_ERROR(201, "错误的用户名或密码"),
    LOGIN_DISABLED(202, "该账户已被禁用，请联系管理员"),

    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态码对应的信息
     */
    private String message;

    MessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MessageEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
