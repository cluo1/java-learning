package cn.mariojd.nearjob.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

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
     * 岗位id
     */
    @NotNull(message = "求职方向还没选呢")
    private Integer jobId;

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
    private Integer distance;

    /**
     * 数据来源
     */
    private Integer sourceFrom;

    /**
     * 搜索关键字
     */
    @Length(max = 20, message = "搜索内容太长啦")
    private String keyword;


}
