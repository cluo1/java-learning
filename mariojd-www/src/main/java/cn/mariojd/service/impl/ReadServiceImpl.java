package cn.mariojd.service.impl;

import cn.mariojd.base.BaseController;
import cn.mariojd.base.BaseService;
import cn.mariojd.dto.PageResult;
import cn.mariojd.entity.Read;
import cn.mariojd.service.ReadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mario
 */
@Service
@Transactional(readOnly = true)
public class ReadServiceImpl extends BaseService implements ReadService {

    @Override
    public List<Read> getList(PageResult page) {
        Map<String, Object> params = new HashMap<>();
        int totalNumber = readDao.getCount();
        page.setTotalNumber(totalNumber);
        params.put("page", page);
        List<Read> readList = readDao.getList(params);
        return readList;
    }
}
