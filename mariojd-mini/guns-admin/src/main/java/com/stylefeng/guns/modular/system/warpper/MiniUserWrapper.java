package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import java.util.Map;
import java.util.Objects;

public class MiniUserWrapper extends BaseControllerWarpper {

    public MiniUserWrapper(Object list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Integer role = (Integer) map.get("role");
        if (Objects.nonNull(role)) {
            map.put("role", ConstantFactory.me().getMiniUserRole(role));
            String cids = (String) map.get("cids");
            if (role == 1) {
                //老师，可能有多个班级
                //map.put("cids", ConstantFactory.me().getClassNameByClassId((Integer)));
            } else {
                //学生，只有一个班级
                map.put("cids", ConstantFactory.me().getClassNameByClassId(Integer.parseInt(cids)));
            }
        }
        map.put("gender", ConstantFactory.me().getSexName((Integer) map.get("gender")));
    }

}
