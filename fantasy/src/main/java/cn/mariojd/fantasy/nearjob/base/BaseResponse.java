package cn.mariojd.fantasy.nearjob.base;

import cn.mariojd.fantasy.service.enums.FantasyEnum;
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
    private FantasyEnum fantasyEnum;

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(FantasyEnum fantasyEnum) {
        this.code = fantasyEnum.getCode();
        this.msg = fantasyEnum.getMsg();
    }

}
