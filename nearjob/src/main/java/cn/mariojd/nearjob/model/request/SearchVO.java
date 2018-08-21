package cn.mariojd.nearjob.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
    private Integer jobId;

    /**
     * 经度
     */
    @Range(min = 73, max = 135, message = "经度范围应在73 - 135°E之间")
    private Float location;

    /**
     * 纬度
     */
    @Range(min = 4, max = 54, message = "纬度范围应在4 - 54°N之间")
    private Float latitude;

    /**
     * 距离范围
     */
    @Range(min = 0, max = 10, message = "搜索范围应在0 - 10km之间")
    private Integer distance;

    /**
     * 数据来源
     */
    private Integer sourceFrom;

    /**
     * 搜索关键字
     */
    @Length(max = 20, message = "关键字搜索限制最大长度为20")
    private String keyword;

}
