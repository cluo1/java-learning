package com.luwei.maven.message;

/**
 * @author jdq
 * @date 2017/11/27 15:02
 */
public class Message {

    /**
     * 对应properties文件中的key
     */
    private String code;

    /**
     * 对应properties文件中的value
     */
    private String msg;

    Message(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    String getCode() {
        return code;
    }

    String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

}
