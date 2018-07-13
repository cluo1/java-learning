package com.luwei.module.alipay.login.service;

import com.luwei.module.alipay.login.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlipayLoginService {

    @Autowired
    private UserDao userDao;


}
