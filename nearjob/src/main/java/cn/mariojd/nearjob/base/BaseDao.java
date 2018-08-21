package cn.mariojd.nearjob.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Jared
 * @date 2018/8/21 16:45
 */
// @See: https://www.jianshu.com/p/7e418e1c0629
@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T, Integer> {

    @Query("SELECT new #{#entityName}(positionId,jobName,jobSalary,jobExperience,jobEducation,jobAdvantage," +
            "companyShortName,companyLocation,postJobTime) FROM #{#entityName} WHERE positionId=?1")
    T findByPositionId(String positionId);

}
