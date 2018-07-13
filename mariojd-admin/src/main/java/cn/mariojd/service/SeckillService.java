package cn.mariojd.service;

import cn.mariojd.base.BaseService;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.Seckill;
import cn.mariojd.enums.MessageEnum;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mario
 */
@Service
@Transactional(readOnly = true)
public class SeckillService extends BaseService {

    @CacheEvict(value = {"seckill", "seckillPages"}, allEntries = true)
    @Transactional
    public MessageResult update(Seckill seckill) {
        Seckill seckill1 = seckillRepository.findOne(seckill.getSid());
        if (null != seckill1) {
            seckill.setCreateTime(seckill1.getCreateTime());
            seckillRepository.saveAndFlush(seckill);
            return new MessageResult(MessageEnum.UPDATESUCCESS);
        } else {
            return new MessageResult(MessageEnum.UPDATEFAILURE);
        }
    }

    @Cacheable(value = "seckill", key = "#sid")
    public Seckill getSeckill(Integer sid) {
        return seckillRepository.findOne(sid);
    }

    @CacheEvict(value = "seckillPages", allEntries = true)
    @Transactional
    public void save(Seckill seckill) {
        seckillRepository.save(seckill);
    }

    @CacheEvict(value = "seckillPages", allEntries = true)
    @Transactional
    public String delete(Integer sid, Integer index, Integer pageNumber) {
        seckillRepository.delete(sid);
        if (index == 0 && pageNumber != 1) {
            pageNumber = pageNumber - 1;
        }
        return "redirect:/seckill/list/" + pageNumber;
    }

    @Cacheable(value = "seckillPages", key = "#pageNumber")
    public Page<Seckill> findAll(Integer pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 5, sort);
        return seckillRepository.findAll(pageRequest);
    }

    @Cacheable(value = "seckill_report")
    public int[] report() {
        List<Seckill> seckillList = seckillRepository.findAll();
        int x = 0, y = 0, z = 0;
        int p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;
        for (Seckill seckill : seckillList) {
            String category = seckill.getScategory();
            if (category.equals("手机")) {
                x++;
            } else if (category.equals("笔记本")) {
                y++;
            } else {
                z++;
            }
            Integer price = seckill.getSprice();
            if (price < 1500) {
                p1++;
            } else if (1500 <= price && price < 3000) {
                p2++;
            } else if (3000 <= price && price < 5000) {
                p3++;
            } else if (5000 <= price && price < 10000) {
                p4++;
            } else {
                p5++;
            }
        }
        return new int[]{x, y, z, p1, p2, p3, p4, p5};
    }
}
