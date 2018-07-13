package com.luwei.module.alipay.login.dao;

import com.luwei.module.alipay.login.pojo.*;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {

    void saveAlipayUserInfo(User user);

}
