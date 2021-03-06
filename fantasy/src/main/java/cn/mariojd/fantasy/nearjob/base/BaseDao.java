package cn.mariojd.fantasy.nearjob.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Jared
 * @date 2018/8/21 16:45
 */
// @See: https://www.jianshu.com/p/7e418e1c0629
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends JpaRepository<T, Integer> {

    @Query(value = "SELECT new #{#entityName}(positionId,jobName,jobSalary,jobExperience,jobEducation,companyScale," +
            "companyShortName,companyFullName,postJobTime,sourceFrom,companyLatitude,companyLongitude,companyLogo) FROM #{#entityName} WHERE positionId=?1")
    T findPartByPositionId(String positionId);

    T findByPositionId(String positionId);

}
