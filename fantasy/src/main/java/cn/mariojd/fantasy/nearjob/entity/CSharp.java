package cn.mariojd.fantasy.entity;

import cn.mariojd.fantasy.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Jared
 * @date 2018/8/21 9:01
 */
@Entity
@Table(name = "tb_csharp")
public class CSharp extends BaseEntity {

    public CSharp() {
    }

    public CSharp(String positionId, String jobName, String jobSalary, String jobExperience, String jobEducation, String companyScale, String companyShortName, String companyFullName, Date postJobTime, Integer sourceFrom, Double companyLatitude, Double companyLongitude, String companyLogo) {
        super(positionId, jobName, jobSalary, jobExperience, jobEducation, companyScale, companyShortName, companyFullName, postJobTime, sourceFrom, companyLatitude, companyLongitude, companyLogo);
    }
}
