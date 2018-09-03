package cn.mariojd.nearjob.entity;

import cn.mariojd.nearjob.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Jared
 * @date 2018/8/21 9:16
 */
@Entity
@Table(name = "tb_python")
public class Python extends BaseEntity {

    public Python() {
    }

    public Python(String positionId, String jobName, String jobSalary, String jobExperience, String jobEducation, String jobAdvantage, String companyShortName, String companyLocation, Date postJobTime, Double companyLatitude, Double companyLongitude) {
        super(positionId, jobName, jobSalary, jobExperience, jobEducation, jobAdvantage, companyShortName, companyLocation, postJobTime, companyLatitude, companyLongitude);
    }

}
