package com.stylefeng.guns.modular.school.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.modular.school.service.ISchoolService;
import com.stylefeng.guns.modular.system.dao.SchoolMapper;
import com.stylefeng.guns.modular.system.model.School;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学校表 服务实现类
 * </p>
 *
 * @author Jared123
 * @since 2018-04-05
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {

    /**
     * 获得所有未删除、未冻结的学校列表
     *
     * @return
     */
    @Override
    public List<School> findList() {
        EntityWrapper<School> entityWrapper = new EntityWrapper<>();
        Wrapper<School> wrapper = entityWrapper.in("status",
                String.valueOf(ManagerStatus.OK.getCode()));
        return selectList(wrapper);
    }
}
