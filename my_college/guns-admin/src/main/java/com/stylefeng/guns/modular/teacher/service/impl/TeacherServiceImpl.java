package com.stylefeng.guns.modular.teacher.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.modular.system.dao.TeacherMapper;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.modular.teacher.service.ITeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 教师表 服务实现类
 * </p>
 *
 * @author Jared123
 * @since 2018-04-06
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    /**
     * 获得所有未删除、未冻结的老师列表
     *
     * @return
     */
    @Override
    public List<Teacher> findList() {
        EntityWrapper<Teacher> entityWrapper = new EntityWrapper<>();
        Wrapper<Teacher> wrapper = entityWrapper.in("status",
                String.valueOf(ManagerStatus.OK.getCode()));
        return selectList(wrapper);
    }

}
