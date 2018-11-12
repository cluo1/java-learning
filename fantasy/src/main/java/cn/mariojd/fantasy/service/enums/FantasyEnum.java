package cn.mariojd.fantasy.service.enums;

/**
 * @author Jared
 * @date 2018/8/22 9:12
 */
public enum FantasyEnum {

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

    FantasyEnum(Integer code, String msg) {
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
