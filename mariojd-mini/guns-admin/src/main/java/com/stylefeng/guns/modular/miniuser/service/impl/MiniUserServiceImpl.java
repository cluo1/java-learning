package com.stylefeng.guns.modular.miniuser.service.impl;

import com.stylefeng.guns.modular.system.model.MiniUser;
import com.stylefeng.guns.modular.system.dao.MiniUserMapper;
import com.stylefeng.guns.modular.miniuser.service.IMiniUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小程序用户表 服务实现类
 * </p>
 *
 * @author Jared123
 * @since 2018-04-07
 */
@Service
public class MiniUserServiceImpl extends ServiceImpl<MiniUserMapper, MiniUser> implements IMiniUserService {

}
