package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.util.DateUtil;

import java.util.Date;
import java.util.Map;

public class CourseWrapper extends BaseControllerWarpper {

    public CourseWrapper(Object list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("startTime", DateUtil.format((Date) map.get("startTime"), "HH:mm"));
        map.put("endTime", DateUtil.format((Date) map.get("endTime"), "HH:mm"));
        map.put("endDate", DateUtil.getDay((Date) map.get("endDate")));
        map.put("startDate", DateUtil.getDay((Date) map.get("startDate")));
        map.put("cid", ConstantFactory.me().getClassNameByClassId((Integer) map.get("cid")));
        map.put("uid", ConstantFactory.me().getUserNameById((Integer) map.get("uid")));
        map.put("status", ConstantFactory.me().getStatusName((Integer) map.get("status")));
        map.put("tid", ConstantFactory.me().getTeacherNameByTeacherId((Integer) map.get("tid")));
        map.put("weekDay", ConstantFactory.me().getWeekDayName((Integer) map.get("weekDay")));
    }

}
