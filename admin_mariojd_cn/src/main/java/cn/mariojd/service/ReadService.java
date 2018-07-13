package cn.mariojd.service;

import cn.mariojd.base.BaseService;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.Read;
import cn.mariojd.enums.MessageEnum;
import org.springframework.cache.annotation.CacheEvict;
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
public class ReadService extends BaseService {


    @CacheEvict(value = {"read", "readPages"}, allEntries = true)
    @Transactional
    public MessageResult update(Read read) {
        Read read1 = readRepository.getOne(read.getRid());
        if (null != read1) {
            readRepository.saveAndFlush(read);
            return new MessageResult(MessageEnum.UPDATESUCCESS);
        } else {
            return new MessageResult(MessageEnum.UPDATEFAILURE);
        }
    }

    @Cacheable(value = "read", key = "#rid")
    public Read getRead(Integer rid) {
        return readRepository.findOne(rid);
    }

    @CacheEvict(value = "readPages", allEntries = true)
    @Transactional
    public void save(Read read) {
        if (read.getUrl().equals("")) {
            read.setUrl("/images/default.jpg");
        }
        readRepository.save(read);
    }

    @CacheEvict(value = "readPages", allEntries = true)
    @Transactional
    public String delete(Integer rid, Integer index, Integer pageNumber) {
        readRepository.delete(rid);
        if (index == 0 && pageNumber != 1) {
            pageNumber = pageNumber - 1;
        }
        return "redirect:/read/list/" + pageNumber;
    }

    @Cacheable(value = "readPages", key = "#pageNumber")
    public Page<Read> findAll(Integer pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "postTime");
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 12, sort);
        return readRepository.findAll(pageRequest);
    }
}
