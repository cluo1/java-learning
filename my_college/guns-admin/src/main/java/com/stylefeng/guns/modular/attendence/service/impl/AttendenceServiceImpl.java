package com.stylefeng.guns.modular.attendence.service.impl;

import com.stylefeng.guns.modular.system.model.Attendence;
import com.stylefeng.guns.modular.system.dao.AttendenceMapper;
import com.stylefeng.guns.modular.attendence.service.IAttendenceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 考勤表 服务实现类
 * </p>
 *
 * @author Jared123
 * @since 2018-05-10
 */
@Service
public class AttendenceServiceImpl extends ServiceImpl<AttendenceMapper, Attendence> implements IAttendenceService {

}
