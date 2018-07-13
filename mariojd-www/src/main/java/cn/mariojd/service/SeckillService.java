package cn.mariojd.service;

import cn.mariojd.dto.SeckillExecution;
import cn.mariojd.dto.SeckillExposure;
import cn.mariojd.entity.Order;
import cn.mariojd.entity.Seckill;

import java.util.List;

/**
 * Created by Mario
 */
public interface SeckillService {

    /**
     * 通过存储过程执行抢购操作,在存储过程中完成了对数据的更改和记录
     *
     * @param order
     * @param sid
     * @param md5
     * @return SeckillExecution
     */
    SeckillExecution executeSeckillByProcedure(Integer sid, String md5, Order order);

    /**
     * 若抢购开启则暴露抢购接口地址，否则输出系统时间
     *
     * @param sid
     * @return SeckillExposure
     */
    SeckillExposure exportSeckillUrl(Integer sid);

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
