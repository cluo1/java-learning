package cn.mariojd.enums;

/**
 * Created by Mario
 */
public enum MessageEnum {

    USER_OPERATION_TIPSUCCESS(true, "提醒成功"),
    USER_OPERATION_TIPBYEMAIL(104, "<h1>您好，马里奥注意到你最近有过在" +
            "<a href='https://www.mariojd.cn'>Happy Mario</a>网进行" +
            "账户注册的行为，如果这是你本人的操作，请尽快激活你的账户，" +
            "谢谢。<a href='https://www.mariojd.cn'>点击进行激活</a><h1>"),
    USER_OPERATION_TIPBYTELEPHONE(104, "Hi，小马留意到你最近有过在" +
            "<a href='https://www.mariojd.cn'>Mario</a>进行注册的行为，如果这是您的操作，请尽快激活。"),
    USER_OPERATION_DISABLESUCCESS(true, "禁用成功"),
    USER_OPERATION_SYSTEMERROR(true, "系统错误，操作失败"),
    USER_OPERATION_RECOVERSUCCESS(true, "恢复成功"),
    USER_OPERATION_SYSTEMUNKNOWN(false, "未知的操作"),

    UPDATESUCCESS(true, "修改成功"),
    UPDATEFAILURE(false, "修改失败"),

    //admin修改
    ADMIN_UPDATESUCCESS(true, "保存成功"),
    ADMIN_UPDATEFAILURE(false, "服务器出错了"),
    //admin登录
    ADMIN_LOGINSUCCESS(true, "登录成功"),
    ADMIN_LOGINFAILURE(false, "错误的用户名或密码"),;

    private Integer code;

    private boolean success;

    private String message;

    MessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    MessageEnum(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MessageEnum{" +
                "code=" + code +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
