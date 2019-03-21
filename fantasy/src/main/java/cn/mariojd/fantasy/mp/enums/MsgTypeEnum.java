package cn.mariojd.fantasy.mp.enums;

import lombok.Getter;

/**
 * @author Jared
 * @date 2019/3/21 13:56
 */
@Getter
public enum MsgTypeEnum {

    /**
     * 1：文字；3：图片；49：图文
     */
    TEXT(1),

    IMAGE(3),

    IMAGE_TEXT(49),
    ;

    private Integer type;

    MsgTypeEnum(Integer type) {
        this.type = type;
    }

}
