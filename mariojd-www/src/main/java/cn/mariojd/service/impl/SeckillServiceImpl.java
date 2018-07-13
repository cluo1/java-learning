package cn.mariojd.service.impl;

import cn.mariojd.base.BaseService;
import cn.mariojd.dto.SeckillExecution;
import cn.mariojd.dto.SeckillExposure;
import cn.mariojd.entity.Order;
import cn.mariojd.entity.Seckill;
import cn.mariojd.enums.SeckillEnum;
import cn.mariojd.service.SeckillService;
import cn.mariojd.util.Md5DigestUtil;
import cn.mariojd.util.RandomUtil;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mario
 */
@Service
@Transactional(readOnly = true)
public class SeckillServiceImpl extends BaseService implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    /**
     * 自定义盐值
     */
    private static final String salt = "%#9f8783%^#@%#32324$%215@";

    @Override
    @Transactional
    public SeckillExecution executeSeckillByProcedure(Integer sid, String md5, Order order) {
        if (md5 == null || (!md5.equals(Md5DigestUtil.getMd5(sid + salt)))) {
            //-3(数据篡改)
            return new SeckillExecution(sid, SeckillEnum.SECKILL_DATA_REWRITE);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String oid = sdf.format(new Date()) + RandomUtil.getRandom();
            Map<String, Object> map = new HashMap<>();
            map.put("oid", oid);
            map.put("uid", order.getUid());
            map.put("sid", sid);
            map.put("sname", order.getSname());
            map.put("sprice", order.getSprice());
            map.put("killTime", new Date());
            map.put("result", null);
            try {
                //执行存储过程，result(对应的值可能为-1,1,0)将被复制
                seckillDao.killByProcedure(map);
                //观察map里面的result值，if the value is null or if the conversion fails就返回-2(此处对应为系统异常)
                int result = MapUtils.getInteger(map, "result", -2);
                if (result == 1) {
                    //执行成功
                    return new SeckillExecution(sid, SeckillEnum.SECKILL_SUCCESS);
                } else {
                    //-1(重复抢购),0(活动结束),-2(系统异常)
                    return new SeckillExecution(sid, SeckillEnum.stateOf(result));
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                //-2(系统异常)
                return new SeckillExecution(sid, SeckillEnum.SECKILL_INNERE_RROR);
            }
        }
    }

    @Override
    public SeckillExposure exportSeckillUrl(Integer sid) {
        //访问redis,redis缓存优化后端优化，在超时的基础上进行一致性的维护
        Seckill seckill = redisDao.getSeckillBySid(sid);
        if (seckill == null) {
            //访问数据库从数据库中获取数据
            seckill = seckillDao.getSeckillBySid(sid);
            if (seckill == null) {
                //数据库中也没有该数据
                return new SeckillExposure(false, sid);
            } else {
                //存在数据就放入redis缓存中,成功响应为OK
                String result = redisDao.putSeckill(seckill);
            }
        }
        Date start = seckill.getStartTime();
        Date end = seckill.getEndTime();
        Date now = new Date();
        if (now.getTime() < start.getTime() ||
                now.getTime() > end.getTime()) {
            //抢购未开启或已结束
            return new SeckillExposure(false, sid, now.getTime(), start.getTime(), end.getTime());
        }
        //MD5盐值加密
        String md5 = Md5DigestUtil.getMd5(sid + salt);
        return new SeckillExposure(true, md5, sid);
    }


    @Override
    public Seckill getSeckillBySid(Integer sid) {
        return seckillDao.getSeckillBySid(sid);
    }

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.getSeckillList();
    }
}
