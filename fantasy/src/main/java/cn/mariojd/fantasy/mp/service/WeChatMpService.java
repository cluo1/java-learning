package cn.mariojd.fantasy.mp.service;

import cn.mariojd.fantasy.mp.dao.WeChatMpDao;
import cn.mariojd.fantasy.mp.entity.WeChatMp;
import cn.mariojd.fantasy.mp.model.response.WeChatResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jared
 * @date 2018/11/23 9:52
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "WeChatMpService")
public class WeChatMpService {

    @Resource
    private WeChatMpDao weChatMpDao;

    @Cacheable(key = "#root.targetClass.simpleName+'.'+#root.methodName")
    public Page<WeChatResultVO> findPage(Pageable pageable) {
        log.info("获取WeChatMp ing...");
        Page<WeChatMp> page = weChatMpDao.findByShowIsTrue(pageable);
        List<WeChatMp> list = new ArrayList<>(page.getContent());
        Collections.shuffle(list);
        List<WeChatResultVO> collect = list.stream().map(weChatMp -> {
            WeChatResultVO resultVO = new WeChatResultVO();
            BeanUtils.copyProperties(weChatMp, resultVO);
            return resultVO;
        }).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, list.size());
    }

}
