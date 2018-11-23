package cn.mariojd.fantasy.mp.dao;

import cn.mariojd.fantasy.mp.entity.WeChatMp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jared
 * @date 2018/11/23 9:47
 */
public interface WeChatMpDao extends JpaRepository<WeChatMp, Integer> {

    Page<WeChatMp> findByShowIsTrue(Pageable pageable);

}
