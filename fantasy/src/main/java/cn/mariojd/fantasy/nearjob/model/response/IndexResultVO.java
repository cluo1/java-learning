package cn.mariojd.fantasy.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Jared
 * @date 2018/8/21 9:51
 */
@Data
public class IndexResultVO {

    /**
     * 标志id
     */
    private String positionId;

    /**
     * 岗位名称
     */
    private String jobName;

    /**
     * 薪酬范围
     */
    private String jobSalary;

    /**
     * 学历要求
     */
    private String jobEducation;

    /**
     * 岗位经验
     */
    private String jobExperience;

    /**
     * 公司规模
     */
    private String companyScale;

    /**
     * 公司简称
     */
    private String companyShortName;

    /**
     * 公司全称
     */
    private String companyFullName;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "MM-dd HH:mm", timezone = "GTM+8")
    private Date postJobTime;

    /**
     * 数据来源
     */
    private Integer sourceFrom;

    /**
     * 两者距离
     */
    private String distance;

    /**
     * 公司logo
     */
    private String companyLogo;

}
