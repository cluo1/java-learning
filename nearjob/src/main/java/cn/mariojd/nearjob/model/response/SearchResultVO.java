package cn.mariojd.nearjob.model.response;

import lombok.Data;

/**
 * @author Jared
 * @date 2018/8/21 9:51
 */
@Data
public class SearchResultVO {

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

}
