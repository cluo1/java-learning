package cn.mariojd.dto;

import cn.mariojd.enums.MessageEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Mario
 */
public class MessageResult<T> {

    private boolean success;

    private String message;

    private T data;

    private List<int[]> report;

    public MessageResult(MessageEnum messageEnum) {
        this.success = messageEnum.isSuccess();
        this.message = messageEnum.getMessage();
    }

    public MessageResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public MessageResult(boolean success, List<int[]> report) {
        this.success = success;
        this.report = report;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<int[]> getReport() {
        return report;
    }

    public void setReport(List<int[]> report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "MessageResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", report=" + report +
                '}';
    }
}
