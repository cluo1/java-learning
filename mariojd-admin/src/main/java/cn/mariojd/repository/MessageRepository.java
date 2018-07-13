package cn.mariojd.repository;

import cn.mariojd.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mario
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
