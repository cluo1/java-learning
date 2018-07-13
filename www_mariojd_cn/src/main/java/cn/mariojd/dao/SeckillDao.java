package cn.mariojd.dao;

import cn.mariojd.entity.Seckill;

import java.util.List;
import java.util.Map;

/**
 * Created by Mario
 */
public interface SeckillDao {

    /**
     * 通过存储过程执行抢购过程
     *
     * @param map
     */
    void killByProcedure(Map<String, Object> map);

    /**
     * 通过sid获取商品详情
     *
     * @param sid
     * @return Seckill
     */
    Seckill getSeckillBySid(Integer sid);

    /**
     * 获取抢购商品列表
     *
     * @return List<Seckill>
     */
    List<Seckill> getSeckillList();

}
