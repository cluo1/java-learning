package cn.mariojd.repository;

import cn.mariojd.entity.Seckill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Mario
 */
public interface SeckillRepository extends JpaRepository<Seckill, Integer> {

}
