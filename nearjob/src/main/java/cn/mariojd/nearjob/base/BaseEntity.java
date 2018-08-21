package cn.mariojd.nearjob.base;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Jared
 * @date 2018/8/21 9:03
 */
@Data
@Entity
// @See: https://blog.csdn.net/mhmyqn/article/details/37996673
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    /**
     * 位置标志(唯一)
     */
    @Column(unique = true)
    private String positionId;

    /**
     * 城市id
     */
    private Integer cityId;

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
     * 公司标志
     */
    private String companyId;

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
    private Float companyLatitude;

    /**
     * 地址经度
     */
    private Float companyLongitude;

    /**
     * 公司主页
     */
    private String companyIndex;

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
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 过期标志
     */
    private Boolean expired;

    /**
     * 过期时间
     */
    private Date expiredTime;

    protected BaseEntity() {
    }

    public BaseEntity(String positionId, String jobName, String jobSalary, String jobExperience,
                      String jobEducation, String jobAdvantage, String companyShortName,
                      String companyLocation, Date postJobTime) {
        this.positionId = positionId;
        this.jobName = jobName;
        this.jobSalary = jobSalary;
        this.jobExperience = jobExperience;
        this.jobEducation = jobEducation;
        this.jobAdvantage = jobAdvantage;
        this.companyShortName = companyShortName;
        this.companyLocation = companyLocation;
        this.postJobTime = postJobTime;
    }

}
