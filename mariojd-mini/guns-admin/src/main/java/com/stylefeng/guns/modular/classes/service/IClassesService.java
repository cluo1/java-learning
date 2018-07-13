package com.stylefeng.guns.modular.classes.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Classes;

import java.util.List;

/**
 * <p>
 * 班级表 服务类
 * </p>
 *
 * @author Jared123
 * @since 2018-04-06
 */
public interface IClassesService extends IService<Classes> {

    /**
     * 获得所有未删除、未冻结的班级列表
     */
    List<Classes> findList();

}
