package cn.mariojd.service;

import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.User;

/**
 * 用户行为的接口
 *
 * @author Mario
 */
public interface UserService {

    /**
     * 根据openid获得微信用户对应的uid
     *
     * @param openid
     * @return User
     */
    User getUserByOpenid(String openid);

    /**
     * 根据openid更新微信用户信息
     *
     * @param openid
     * @param nickname
     * @param icon
     */
    void updateWeixinUserByOpenid(String openid, String nickname, String icon);

    /**
     * 保存微信用户信息
     */
    void saveWeixinUser(String openid, String nickname, String icon);

    /**
     * 根据uid更新nickname
     *
     * @param uid
     * @param nickname
     */
    void updateNicknameByUid(Integer uid, String nickname);

    /**
     * 更新发布的微博数量
     *
     * @param uid
     */
    void updateCount(Integer uid);

    /**
     * 根据uid更新username
     *
     * @param uid
     * @param username
     */
    void updateUsernameByUid(Integer uid, String username);

    /**
     * 根据uid检查code
     *
     * @param uid
     * @param code
     * @return MessageResult
     */
    MessageResult checkCodeByUid(Integer uid, String code);

    /**
     * 根据uid保存code，根据username发送code
     *
     * @param uid
     * @param username
     */
    void updateCodeByUid(Integer uid, String username);

    /**
     * 根据uid保存头像地址
     *
     * @param uid
     * @param icon
     */
    void saveIconByUid(Integer uid, String icon);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return User
     */
    User getUserByUid(Integer uid);

    /**
     * 通过username获取User
     *
     * @param username
     * @return User
     */
    User getUserByUsername(String username);

    /**
     * 根据username和password更新User
     *
     * @param username
     * @param password
     */
    void updatePswByUsername(String username, String password);

    /**
     * 根据用户名和code校验 User
     *
     * @param username
     * @param code
     * @return MessageResult
     */
    MessageResult checkUserByUsernameAndCode(String username, String code);

    /**
     * 激活、找回时根据telephone/email更新code
     *
     * @param username
     */
    void updateCodeByUsername(String username);


    /**
     * 通过telephone和code获取User
     *
     * @param telephone
     * @param code
     * @param password
     * @return MessageResult
     */
    MessageResult getUserByTelAndCode(String telephone, String code, String password);

    /**
     * 通过email和code获取User
     *
     * @param email
     * @param code
     * @return MessageResult
     */
    MessageResult activeByEmail(String email, String code);

    /**
     * 手机、邮箱注册更新psw and code
     *
     * @param username
     * @param password
     */
    void updatePswAndCode(String username, String password);

    /**
     * 保存User
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 根据用户名校验用户state
     *
     * @param username
     * @return MessageResult
     */
    MessageResult checkStateByUsername(String username);
}
