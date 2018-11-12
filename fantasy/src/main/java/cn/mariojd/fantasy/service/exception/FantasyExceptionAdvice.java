package cn.mariojd.fantasy.service.exception;

import cn.mariojd.fantasy.nearjob.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 统一异常处理(切面)
 *
 * @author Jared
 * @date 2018/8/22 9:17
 */
@Slf4j
@RestControllerAdvice
public class FantasyExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FantasyException.class)
    public BaseResponse jobException(FantasyException e) {
        log.error("FantasyException: {}", e);
        return new BaseResponse(e.getFantasyEnum());
    }

}
