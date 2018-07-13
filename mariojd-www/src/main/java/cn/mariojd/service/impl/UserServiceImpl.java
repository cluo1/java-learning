package cn.mariojd.service.impl;

import cn.mariojd.base.BaseService;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.User;
import cn.mariojd.enums.MessageEnum;
import cn.mariojd.service.UserService;
import cn.mariojd.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mario
 */
@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

    @Override
    @Transactional(readOnly = true)
    public User getUserByOpenid(String openid) {
        return userDao.getUserByOpenid(openid);
    }

    @Override
    public void updateWeixinUserByOpenid(String openid, String nickname, String icon) {
        userDao.updateUserByOpenid(openid, nickname, icon);
    }

    @Override
    public void saveWeixinUser(String openid, String nickname, String icon) {
        userDao.saveWeixinUser(openid, nickname, icon);
    }

    @Override
    public void updateNicknameByUid(Integer uid, String nickname) {
        userDao.updateNicknameByUid(uid, nickname);
    }

    @Override
    public void updateCount(Integer uid) {
        userDao.updateCount(uid);
    }

    @Override
    public void updateUsernameByUid(Integer uid, String username) {
        if (username.contains(".")) {
            // 更新邮箱
            userDao.updEmailAndCodeByUid(uid, username, null);
        } else {
            // 更新手机
            userDao.updTelAndCodeByUid(uid, username, null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public MessageResult checkCodeByUid(Integer uid, String code) {
        String userCode = userDao.getCodeByUid(uid, code);
        if (userCode == null || userCode.equals("")) {
            return new MessageResult(false, "错误的用户名或验证码");
        } else {
            return new MessageResult(true, "操作成功");
        }
    }

    @Override
    public void updateCodeByUid(Integer uid, String username) {
        String code = RandomUtil.getRandom();
        if (username.contains(".")) {
            // 发邮箱
            userDao.updateCodeByUid(uid, code);
            AliSendMailUtil.sendMail(username, MessageEnum.EMAIL_BINDING, code);
        } else {
            // 发手机
            userDao.updateCodeByUid(uid, code);
            AliSendMessageUtil.sendMessage(username, code);
        }
    }

    @Override
    public void saveIconByUid(Integer uid, String icon) {
        userDao.saveIconByUid(uid, icon);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUid(Integer uid) {
        return userDao.getUserByUid(uid);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        User user = userDao.getUserByUsername(username);
        return user;
    }

    @Override
    public void updatePswByUsername(String username, String password) {
        User user = userDao.getUserByUsername(username);
        user.setPassword(password);
        String salt = UUIDUtil.getUUID();
        user.setSalt(salt);
        user.setPassword(Md5DigestUtil.getMd5(Md5DigestUtil.getMd5(user.getPassword()) + salt));
        user.setCode(null);
        userDao.updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public MessageResult checkUserByUsernameAndCode(String username, String code) {
        User user = userDao.getUserByUsernameAndCode(username, code);
        if (user == null) {
            return new MessageResult(false, "错误的用户名或密码");
        }
        return new MessageResult(true, "正确的用户名和验证码");
    }

    @Override
    public void updateCodeByUsername(String username) {
        String code = RandomUtil.getRandom();
        if (username.contains(".")) {
            AliSendMailUtil.sendMail(username, MessageEnum.EMAIL_VERIFICATION, code);
            userDao.updateCodeByUsername(username, code);
        } else {
            AliSendMessageUtil.sendMessage(username, code);
            userDao.updateCodeByUsername(username, code);
        }
    }


    @Override
    public MessageResult getUserByTelAndCode(String telephone, String code, String password) {
        User user = userDao.getUserByUsernameAndCode(telephone, code);
        if (user == null) {
            // 用户名或验证码有误
            return new MessageResult(MessageEnum.TELEPHONE_ERROR_MESSAGE);
        } else {
            if (user.getState().equals("0")) {
                user.setCode(null);
                user.setState(1);
                String salt = UUIDUtil.getUUID();
                user.setSalt(salt);
                user.setPassword(Md5DigestUtil.getMd5(Md5DigestUtil.getMd5(password) + salt));
                // 手机激活更新code
                userDao.updateUser(user);
                return new MessageResult(MessageEnum.TELEPHONE_SUCCESS_MESSAGE);
            } else if (user.getState().equals("1")) {
                return new MessageResult(MessageEnum.TELEPHONE_ISREGISTERED_MESSAGE);
            } else {
                return new MessageResult(MessageEnum.TELEPHONE_ISDISABLED_MESSAGE);
            }
        }
    }

    @Override
    public MessageResult activeByEmail(String email, String code) {
        User user = userDao.getUserByUsernameAndCode(email, code);
        if (user == null) {
            return new MessageResult(MessageEnum.EMAIL_UNKNOWN_MESSAGE);
        } else {
            if (user.getState().equals(0)) {
                user.setCode(null);
                user.setState(1);
                // 邮箱激活更新code
                userDao.updateUser(user);
                return new MessageResult(MessageEnum.EMAIL_SUCCESS_MESSAGE);
            } else if (user.getState().equals(1)) {
                return new MessageResult(MessageEnum.EMAIL_ERROO_MESSAGE);
            } else {
                return new MessageResult(MessageEnum.EMAIL_ISDISABLE_MESSAGE);
            }
        }
    }

    @Override
    public void updatePswAndCode(String username, String password) {
        User user = userDao.getUserByUsername(username);
        String salt = UUIDUtil.getUUID();
        user.setSalt(salt);
        user.setPassword(Md5DigestUtil.getMd5(Md5DigestUtil.getMd5(password) + salt));
        if (user.getEmail() != null) {
            // 邮箱注册更新psw&code
            String code = UUIDUtil.getUUID();
            user.setCode(code);
            userDao.updateUser(user);
            AliSendMailUtil.sendMail(username, MessageEnum.EMAIL_ACTIVATE, code);
        } else {
            // 手机注册更新psw&code
            String code = RandomUtil.getRandom();
            user.setCode(code);
            userDao.updateUser(user);
            AliSendMessageUtil.sendMessage(username, code);
        }
    }

    @Override
    public void saveUser(User user) {
        String salt = UUIDUtil.getUUID();
        user.setSalt(salt);
        user.setPassword(Md5DigestUtil.getMd5(Md5DigestUtil.getMd5(user.getPassword()) + salt));
        if (user.getEmail() != null) {
            // 邮箱注册
            String code = UUIDUtil.getUUID();
            user.setCode(code);
            AliSendMailUtil.sendMail(user.getEmail(), MessageEnum.EMAIL_ACTIVATE, code);
        } else {
            // 手机注册
            String code = RandomUtil.getRandom();
            user.setCode(code);
            AliSendMessageUtil.sendMessage(user.getTelephone(), code);
        }
        userDao.saveUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public MessageResult checkStateByUsername(String username) {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            return new MessageResult(MessageEnum.NONEXISTENT_USER_MESSAGE);
        }
        return checkState(user.getState());
    }

}
