package cn.mariojd.fantasy.mp.service;

import cn.mariojd.fantasy.mp.dao.WeChatMpDao;
import cn.mariojd.fantasy.mp.entity.WeChatMp;
import cn.mariojd.fantasy.mp.model.response.WeChatResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2018/11/23 9:52
 */
@Slf4j
@Service
public class WeChatMpService {

    @Resource
    private WeChatMpDao weChatMpDao;

    public Page<WeChatResultVO> findPage(Pageable pageable) {
        log.info("获取WeChatMp ing...");
        return weChatMpDao.findByShowIsTrue(pageable).map(this::toWeChatResultVO);
    }

    /**
     * WeChatMp -> WeChatResultVO
     *
     * @param weChatMp
     * @return
     */
    private WeChatResultVO toWeChatResultVO(WeChatMp weChatMp) {
        WeChatResultVO resultVO = new WeChatResultVO();
        BeanUtils.copyProperties(weChatMp, resultVO);
        return resultVO;
    }

}
