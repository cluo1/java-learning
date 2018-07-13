package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import java.util.Map;

public class SchoolWrapper extends BaseControllerWarpper {

    public SchoolWrapper(Object list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("pid", ConstantFactory.me().getProvinceNameByProvinceId((Integer) map.get("pid")));
        map.put("status", ConstantFactory.me().getStatusName((Integer) map.get("status")));
    }

}
