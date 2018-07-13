package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import java.util.Map;

/**
 * 省份列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class ProvinceWrapper extends BaseControllerWarpper {

    public ProvinceWrapper(Object list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("status", ConstantFactory.me().getStatusName((Integer) map.get("status")));
    }
}
