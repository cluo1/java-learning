package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import java.util.Map;

public class TeacherWrapper extends BaseControllerWarpper {

    public TeacherWrapper(Object list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("sid", ConstantFactory.me().getSchoolNameBySchoolId((Integer) map.get("sid")));
        map.put("gender", ConstantFactory.me().getSexName((Integer) map.get("gender")));
        map.put("status", ConstantFactory.me().getStatusName((Integer) map.get("status")));
    }
}
