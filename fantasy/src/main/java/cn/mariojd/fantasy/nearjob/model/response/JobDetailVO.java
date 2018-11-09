package cn.mariojd.fantasy.nearjob.model.response;

import lombok.Data;

import java.util.Date;

/**
 * @author Jared
 * @date 2018/8/23 9:19
 */
@Data
public class JobDetailVO {

    /**
     * 城市名称
     */
    private String city;

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
     * 岗位标签
     */
    private String jobLabel;

    /**
     * 岗位描述
     */
    private String jobDescription;

    /**
     * 发布时间
     */
    private Date postJobTime;

    /**
     * 公司简称
     */
    private String companyShortName;

    /**
     * 公司全称
     */
    private String companyFullName;

    /**
     * 公司地址
     */
    private String companyLocation;

    /**
     * 地址纬度
     */
    private Double companyLatitude;

    /**
     * 地址经度
     */
    private Double companyLongitude;

    /**
     * 融资情况
     */
    private String companyFinance;

    /**
     * 所处行业
     */
    private String companyIndustry;

    /**
     * 企业规模
     */
    private String companyScale;

    /**
     * 公司区域
     */
    private String companyZone;

    /**
     * 数据来源
     */
    private Integer sourceFrom;

    /**
     * 原文地址
     */
    private String sourceUrl;

    /**
     * 公司logo
     */
    private String companyLogo;

}
