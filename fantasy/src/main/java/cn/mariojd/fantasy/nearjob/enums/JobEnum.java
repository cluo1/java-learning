package cn.mariojd.fantasy.nearjob.enums;

/**
 * @author Jared
 * @date 2018/8/22 9:12
 */
public enum JobEnum {

    /**
     * 成功
     */
    SUCCESS(0, "success"),

    /**
     * 失败
     */
    ERROR(1, "error");

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 对应信息
     */
    private String msg;

    JobEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
