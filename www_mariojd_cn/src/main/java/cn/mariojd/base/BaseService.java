package cn.mariojd.base;

import cn.mariojd.dao.*;
import cn.mariojd.dao.cache.RedisDao;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.enums.MessageEnum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Mario
 */
public class BaseService {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected MessageDao messageDao;

    @Autowired
    protected SeckillDao seckillDao;

    @Autowired
    protected RedisDao redisDao;

    @Autowired
    protected NoticeDao noticeDao;

    @Autowired
    protected ReadDao readDao;

    /**
     * 校验用户state的方法
     *
     * @param state
     * @return MessageResult
     */
    public MessageResult checkState(Integer state) {
        if (state.equals(0)) {
            return new MessageResult(MessageEnum.INACTIVE_USER_MESSAGE);
        } else if (state.equals(1)) {
            return new MessageResult(MessageEnum.NORMAL_USER_MESSAGE);
        } else {
            return new MessageResult(MessageEnum.DISABLED_USER_MESSAGE);
        }
    }


}
