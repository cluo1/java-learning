package cn.mariojd.repository;

import cn.mariojd.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Mario
 */
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

}
