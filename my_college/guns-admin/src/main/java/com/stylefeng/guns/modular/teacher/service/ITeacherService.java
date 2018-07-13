package com.stylefeng.guns.modular.teacher.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Teacher;

import java.util.List;

/**
 * <p>
 * 教师表 服务类
 * </p>
 *
 * @author Jared123
 * @since 2018-04-06
 */
public interface ITeacherService extends IService<Teacher> {

    /**
     * 获得所有未删除、未冻结的老师列表
     */
    List<Teacher> findList();

}
