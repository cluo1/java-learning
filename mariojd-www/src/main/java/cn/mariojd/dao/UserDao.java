package cn.mariojd.dao;

import cn.mariojd.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Mario
 */
public interface UserDao {

    /**
     * 根据openid获得微信用户信息
     *
     * @param openid
     * @return user
     */
    User getUserByOpenid(String openid);

    /**
     * 根据openid更新微信用户信息
     *
     * @param openid
     * @param nickname
     * @param icon
     */
    void updateUserByOpenid(@Param("openid") String openid, @Param("nickname") String nickname,
                                  @Param("icon") String icon);

    /**
     * 保存微信用户信息
     */
    void saveWeixinUser(@Param("openid") String openid, @Param("nickname") String nickname, @Param("icon") String icon);

    /**
     * 根据uid更新nickname
     *
     * @param uid
     * @param nickname
     */
    void updateNicknameByUid(@Param("uid") Integer uid, @Param("nickname") String nickname);

    /**
     * 更新发布的微博数量
     *
     * @param uid
     */
    void updateCount(Integer uid);

    /**
     * 根据uid更新code和email
     *
     * @param uid
     * @param telephone
     * @param code
     */
    void updTelAndCodeByUid(@Param("uid") Integer uid, @Param("telephone") String telephone,
                            @Param("code") String code);

    /**
     * 根据uid更新code和email
     *
     * @param uid
     * @param email
     * @param code
     */
    void updEmailAndCodeByUid(@Param("uid") Integer uid, @Param("email") String email, @Param("code") String code);

    /**
     * 根据uid查找code
     *
     * @param uid
     * @param code
     * @return String
     */
    String getCodeByUid(@Param("uid") Integer uid, @Param("code") String code);

    /**
     * 根据uid更新code
     *
     * @param uid
     * @param code
     */
    void updateCodeByUid(@Param("uid") Integer uid, @Param("code") String code);

    /**
     * 根据uid保存头像地址
     *
     * @param uid
     * @param icon
     */
    void saveIconByUid(@Param("uid") Integer uid, @Param("icon") String icon);

    /**
     * 根据uid查询用户
     *
     * @param uid
     * @return User
     */
    User getUserByUid(Integer uid);

    /**
     * 根据username和code修改User
     *
     * @param code
     * @param username
     */
    void updateCodeByUsername(@Param("username") String username, @Param("code") String code);

    /**
     * 通过username和code获取User
     *
     * @param username
     * @param code
     * @return User
     */
    User getUserByUsernameAndCode(@Param("username") String username, @Param("code") String code);

    /**
     * 修改User
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 保存User
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 通过username获取User
     *
     * @param username
     * @return User
     */
    User getUserByUsername(@Param("username") String username);

}
