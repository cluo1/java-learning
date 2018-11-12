package cn.mariojd.fantasy.service.exception;

import cn.mariojd.fantasy.service.enums.FantasyEnum;

/**
 * 自定义异常
 *
 * @author Jared
 * @date 2018/8/21 19:17
 */
public class FantasyException extends RuntimeException {

    private FantasyEnum fantasyEnum;

    public FantasyException(FantasyEnum fantasyEnum) {
        this.fantasyEnum = fantasyEnum;
    }

    public FantasyException(String message, FantasyEnum fantasyEnum) {
        super(message);
        this.fantasyEnum = fantasyEnum;
    }

    FantasyEnum getFantasyEnum() {
        return fantasyEnum;
    }

}
