package com.stylefeng.guns.modular.student.service.impl;

import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.modular.system.dao.StudentMapper;
import com.stylefeng.guns.modular.student.service.IStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author Jared123
 * @since 2018-04-06
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
