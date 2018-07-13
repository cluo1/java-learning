package cn.mariojd.service.impl;

import cn.mariojd.base.BaseService;
import cn.mariojd.dto.PageResult;
import cn.mariojd.entity.Message;
import cn.mariojd.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class MessageServiceImpl extends BaseService implements MessageService {

    @Override
    public List<Message> getList(PageResult page) {
        Map<String, Object> params = new HashMap<>();
        int totalNumber = messageDao.getCount();
        page.setTotalNumber(totalNumber);
        params.put("page", page);
        return messageDao.getList(params);
    }

    @Override
    @Transactional
    public void save(Integer uid, String content) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String postTime = sdf.format(new Date());
        messageDao.save(uid, content, postTime);
    }

}
