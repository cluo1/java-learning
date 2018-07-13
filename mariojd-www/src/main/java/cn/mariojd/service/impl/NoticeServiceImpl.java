package cn.mariojd.service.impl;

import cn.mariojd.base.BaseService;
import cn.mariojd.entity.Notice;
import cn.mariojd.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mario
 */
@Service
@Transactional(readOnly = true)
public class NoticeServiceImpl extends BaseService implements NoticeService {

    @Override
    @Transactional
    public void updateVisit(Integer nid) {
        noticeDao.updateVisit(nid);
    }


    @Override
    public List<Notice> getTop5Notices() {
        return noticeDao.getTop5Notices();
    }
}
