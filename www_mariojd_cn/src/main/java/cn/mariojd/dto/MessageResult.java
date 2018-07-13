package cn.mariojd.dto;

import cn.mariojd.enums.MessageEnum;

/**
 * Created by Mario
 * 信息提示的dto对象
 */
public class MessageResult {

    public Boolean flag;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态码对应的信息
     */
    private String message;

    public MessageResult(Boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public MessageResult(MessageEnum messageEnum) {
        this.code = messageEnum.getCode();
        this.message = messageEnum.getMessage();
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageResult{" +
                "flag=" + flag +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
