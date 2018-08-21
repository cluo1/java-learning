package cn.mariojd.nearjob.base;

import lombok.Data;

/**
 * @author Jared
 * @date 2018/8/21 10:27
 */
@Data
public class BaseDocument {

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
    private Float location;

    /**
     * 纬度
     */
    private Float latitude;

    /**
     * 数据来源
     */
    private Integer sourceFrom;

    /**
     * 关键字
     */
    private String keyword;

}
