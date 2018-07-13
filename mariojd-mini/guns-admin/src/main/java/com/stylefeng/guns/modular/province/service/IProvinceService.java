package com.stylefeng.guns.modular.province.service;

import com.stylefeng.guns.modular.system.model.Province;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 省份表 服务类
 * </p>
 *
 * @author Jared123
 * @since 2018-04-05
 */
public interface IProvinceService extends IService<Province> {

    /**
     * 获得所有未删除、未冻结的省份信息
     */
    List<Province> findList();

}
