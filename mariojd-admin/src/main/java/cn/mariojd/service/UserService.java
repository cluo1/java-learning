package cn.mariojd.service;

import cn.mariojd.base.BaseService;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.User;
import cn.mariojd.enums.MessageEnum;
import cn.mariojd.util.AliSendMailUtil;
import cn.mariojd.util.AliSendMessageUtil;
import cn.mariojd.util.Md5DigestUtil;
import cn.mariojd.util.UUIDUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mario
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseService {

    @CacheEvict(value = {"user", "userPages"}, allEntries = true)
    @Transactional
    public MessageResult update(User user) {
        User user1 = userRepository.findOne(user.getUid());
        if (null != user1) {
            if (null != user.getNickname()) {
                user1.setNickname(user.getNickname());
            }
            if (null != user.getTelephone()) {
                user1.setTelephone(user.getTelephone());
            }
            if (null != user.getEmail()) {
                user1.setEmail(user.getEmail());
            }
            userRepository.saveAndFlush(user1);
            return new MessageResult(MessageEnum.UPDATESUCCESS);
        } else {
            return new MessageResult(MessageEnum.UPDATEFAILURE);
        }
    }

    @Cacheable(value = "user", key = "#uid")
    public User getUser(Integer uid) {
        return userRepository.findOne(uid);
    }

    @CacheEvict(value = "userPages", allEntries = true)
    @Transactional
    public void save(User user) {
        String salt = UUIDUtil.getUUID();
        user.setSalt(salt);
        user.setPassword(Md5DigestUtil.getMd5(Md5DigestUtil.getMd5("123456") + salt));
        user.setCount(0);
        user.setState(1);
        userRepository.save(user);
    }

    @CacheEvict(value = "userPages", key = "#pageNumber")
    @Transactional
    public MessageResult operation(User user, String account, Integer pageNumber) {
        Integer state = user.getState();
        if (state.equals(0)) {
            if (account.contains(".")) {
                AliSendMailUtil.sendMail(account, MessageEnum.USER_OPERATION_TIPBYEMAIL, null);
                //邮件提醒
            } else {
                //短信提醒
                AliSendMessageUtil.sendMessage(account, null, MessageEnum.USER_OPERATION_TIPBYTELEPHONE);
            }
            return new MessageResult(MessageEnum.USER_OPERATION_TIPSUCCESS);
        } else if (state.equals(1)) {
            //禁用
            int count = userRepository.updateStateByUid(user.getUid(), 2);
            if (count > 0) {
                return new MessageResult(MessageEnum.USER_OPERATION_DISABLESUCCESS);
            }
            return new MessageResult(MessageEnum.USER_OPERATION_SYSTEMERROR);
        } else if (state.equals(2)) {
            //解除
            int count = userRepository.updateStateByUid(user.getUid(), 1);
            if (count > 0) {
                return new MessageResult(MessageEnum.USER_OPERATION_RECOVERSUCCESS);
            }
            return new MessageResult(MessageEnum.USER_OPERATION_SYSTEMERROR);
        } else {
            //未知
            return new MessageResult(MessageEnum.USER_OPERATION_SYSTEMUNKNOWN);
        }
    }

    @CacheEvict(value = "userPages", allEntries = true)
    @Transactional
    public String delete(Integer uid, Integer index, Integer pageNumber) {
        userRepository.delete(uid);
        if (index == 0 && pageNumber != 1) {
            pageNumber = pageNumber - 1;
        }
        return "redirect:/user/list/" + pageNumber;
    }

    @Cacheable(value = "userPages", key = "#pageNumber")
    public Page<User> findAll(Integer pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "uid");
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 5, sort);
        return userRepository.findAll(pageRequest);
    }

    @Cacheable(value = "user_report")
    public int[] report() {
        List<User> userList = userRepository.findAll();
        int totalUser = 0, wechatUser = 0, autonomousUser = 0, disabledUser = 0, inactiveUser = 0;
        for (User user : userList) {
            if (user.getState().equals(0)) {
                inactiveUser++;
            } else {
                if (user.getOpenid() != null && user.getOpenid().length() > 0) {
                    wechatUser++;
                }
                if (user.getState().equals(2)) {
                    disabledUser++;
                }
            }
        }
        totalUser = userList.size() - inactiveUser;
        autonomousUser = totalUser - wechatUser;
        return new int[]{totalUser, wechatUser, autonomousUser, disabledUser, inactiveUser};
    }
}
