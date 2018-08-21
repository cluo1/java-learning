package cn.mariojd.nearjob.entity;

import cn.mariojd.nearjob.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Jared
 * @date 2018/8/21 9:01
 */
@Entity
@Table(name = "tb_java")
public class Java extends BaseEntity {

    public Java(Integer id, String jobName, String jobSalary, String jobExperience, String jobEducation, String jobAdvantage, String companyShortName, String companyLocation, Date postJobTime) {
        super(id, jobName, jobSalary, jobExperience, jobEducation, jobAdvantage, companyShortName, companyLocation, postJobTime);
    }

}
