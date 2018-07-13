package cn.mariojd.repository;

import cn.mariojd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Mario
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query("update User u set u.state=?2 where u.uid=?1")
    int updateStateByUid(Integer uid, Integer state);

}
