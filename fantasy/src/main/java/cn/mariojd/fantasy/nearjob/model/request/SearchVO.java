package cn.mariojd.fantasy.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;

/**
 * @author Jared
 * @date 2018/8/21 9:36
 */
@Data
public class SearchVO {

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 岗位id(默认：1-Java)
     */
    private Integer jobId = 1;

    /**
     * 经度
     */
    @Digits(integer = 3, fraction = 30, message = "经度坐标异常")
    private Double longitude;

    /**
     * 纬度
     */
    @Digits(integer = 2, fraction = 30, message = "纬度坐标异常")
    private Double latitude;

    /**
     * 距离范围
     */
    @Range(min = 0, max = 10, message = "搜索范围太广啦")
    private Integer distance = 3;

    /**
     * 数据来源
     */
    private Integer sourceFrom;

    /**
     * 搜索关键字
     */
    private String keyword;

}
