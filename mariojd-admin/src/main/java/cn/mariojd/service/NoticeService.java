package cn.mariojd.service;

import cn.mariojd.base.BaseService;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.Notice;
import cn.mariojd.enums.MessageEnum;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mario
 */
@Service
@Transactional(readOnly = true)
public class NoticeService extends BaseService {

    @CacheEvict(value = {"notice", "noticePages"}, allEntries = true)
    @Transactional
    public MessageResult update(Notice notice) {
        Notice notice1 = noticeRepository.findOne(notice.getNid());
        if (null != notice1) {
            notice1.setTitle(notice.getTitle());
            notice1.setContent(notice.getContent());
            noticeRepository.saveAndFlush(notice1);
            return new MessageResult(MessageEnum.UPDATESUCCESS);
        } else {
            return new MessageResult(MessageEnum.UPDATEFAILURE);
        }
    }

    @Cacheable(value = "notice", key = "#nid")
    public Notice getNotice(Integer nid) {
        return noticeRepository.findOne(nid);
    }

    @CacheEvict(value = "noticePages", allEntries = true)
    @Transactional
    public void save(Notice notice) {
        notice.setVisit(0);
        noticeRepository.save(notice);
    }

    @CacheEvict(value = "noticePages", allEntries = true)
    @Transactional
    public String delete(Integer nid, Integer index, Integer pageNumber) {
        noticeRepository.delete(nid);
        if (index == 0 && pageNumber != 1) {
            pageNumber = pageNumber - 1;
        }
        return "redirect:/notice/list/" + pageNumber;
    }

    @Cacheable(value = "noticePages", key = "#pageNumber")
    public Page<Notice> findAll(Integer pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "postTime");
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 5, sort);
        return noticeRepository.findAll(pageRequest);
    }
}
