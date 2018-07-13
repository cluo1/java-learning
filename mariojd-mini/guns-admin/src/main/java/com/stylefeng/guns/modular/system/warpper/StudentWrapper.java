package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import java.util.Map;

public class StudentWrapper extends BaseControllerWarpper {

    public StudentWrapper(Object list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("sid", ConstantFactory.me().getSchoolNameBySchoolId((Integer) map.get("sid")));
        map.put("cid", ConstantFactory.me().getClassNameByClassId((Integer) map.get("cid")));
        map.put("role", ConstantFactory.me().getStudentRole((Integer) map.get("role")));
        map.put("gender", ConstantFactory.me().getSexName((Integer) map.get("gender")));
        map.put("status", ConstantFactory.me().getStatusName((Integer) map.get("status")));
    }

}
