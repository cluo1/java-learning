package com.stylefeng.guns.modular.province.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.modular.province.service.IProvinceService;
import com.stylefeng.guns.modular.system.dao.ProvinceMapper;
import com.stylefeng.guns.modular.system.model.Province;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 省份表 服务实现类
 * </p>
 *
 * @author Jared123
 * @since 2018-04-05
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements IProvinceService {

    /**
     * 获得所有未删除、未冻结的省份信息
     */
    @Override
    public List<Province> findList() {
        EntityWrapper<Province> entityWrapper = new EntityWrapper<>();
        Wrapper<Province> wrapper = entityWrapper.
                in("status", String.valueOf(ManagerStatus.OK.getCode()));
        List<Province> provinceList = selectList(wrapper);
        return provinceList;
    }

}
