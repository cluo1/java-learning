package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import java.util.Map;

public class AnnouncementWrapper extends BaseControllerWarpper {

    public AnnouncementWrapper(Object list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("cid", ConstantFactory.me().getClassNameByClassId((Integer) map.get("cid")));
        map.put("status", ConstantFactory.me().getStatusName((Integer) map.get("status")));
        map.put("uid", ConstantFactory.me().getUserNameById((Integer) map.get("uid")));
    }
}
