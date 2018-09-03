package cn.mariojd.nearjob.model.response;

import lombok.Data;

import java.util.Date;

/**
 * @author Jared
 * @date 2018/8/21 9:51
 */
@Data
public class IndexResultVO {

    /**
     * 唯一标志
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
     * 岗位经验
     */
    private String jobExperience;

    /**
     * 学历要求
     */
    private String jobEducation;

    /**
     * 岗位优势
     */
    private String jobAdvantage;

    /**
     * 公司简称
     */
    private String companyShortName;

    /**
     * 公司地址
     */
    private String companyLocation;

    /**
     * 发布时间
     */
    private Date postJobTime;

    /**
     * 两者距离
     */
    private Double distance;

}
