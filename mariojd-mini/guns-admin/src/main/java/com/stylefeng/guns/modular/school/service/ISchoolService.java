package com.stylefeng.guns.modular.school.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.School;

import java.util.List;

/**
 * <p>
 * 学校表 服务类
 * </p>
 *
 * @author Jared123
 * @since 2018-04-05
 */
public interface ISchoolService extends IService<School> {

    /**
     * 获得所有未删除、未冻结的学校列表
     */
    List<School> findList();

}
