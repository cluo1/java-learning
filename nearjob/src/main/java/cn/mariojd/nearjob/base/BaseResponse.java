package cn.mariojd.nearjob.base;

import cn.mariojd.nearjob.enums.JobEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jared
 * @date 2018/8/22 9:09
 */
@Data
@AllArgsConstructor
public class BaseResponse<T> {

    private Integer code;

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonIgnore
    private JobEnum jobEnum;

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(JobEnum jobEnum) {
        this.code = jobEnum.getCode();
        this.msg = jobEnum.getMsg();
    }

}
