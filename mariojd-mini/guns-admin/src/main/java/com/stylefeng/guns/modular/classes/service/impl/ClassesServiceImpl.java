package com.stylefeng.guns.modular.classes.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.modular.classes.service.IClassesService;
import com.stylefeng.guns.modular.system.dao.ClassesMapper;
import com.stylefeng.guns.modular.system.model.Classes;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 班级表 服务实现类
 * </p>
 *
 * @author Jared123
 * @since 2018-04-06
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements IClassesService {

    /**
     * 获得所有未删除、未冻结的班级列表
     */
    @Override
    public List<Classes> findList() {
        EntityWrapper<Classes> entityWrapper = new EntityWrapper<>();
        Wrapper<Classes> wrapper = entityWrapper.in("status",
                String.valueOf(ManagerStatus.OK.getCode()));
        return selectList(wrapper);
    }
}
