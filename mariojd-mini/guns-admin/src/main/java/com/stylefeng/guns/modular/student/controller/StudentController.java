package com.stylefeng.guns.modular.student.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.classes.service.IClassesService;
import com.stylefeng.guns.modular.school.service.ISchoolService;
import com.stylefeng.guns.modular.student.service.IStudentService;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.modular.system.warpper.StudentWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学生控制器
 *
 * @author fengshuonan
 * @Date 2018-04-06 13:10:18
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

    private String PREFIX = "/student/";

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ISchoolService schoolService;

    @Autowired
    private IClassesService classesService;

    /**
     * 跳转到学生首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "student.html";
    }

    /**
     * 跳转到添加学生
     */
    @RequestMapping("/student_add")
    public String studentAdd(Model model) {
        model.addAttribute("schools", schoolService.findList());
        model.addAttribute("classes", classesService.findList());
        return PREFIX + "student_add.html";
    }

    /**
     * 跳转到修改学生
     */
    @RequestMapping("/student_update/{studentId}")
    public String studentUpdate(@PathVariable Integer studentId, Model model) {
        Student student = studentService.selectById(studentId);
        model.addAttribute("item", student);
        model.addAttribute("schools", schoolService.findList());
        model.addAttribute("classes", classesService.findList());
        LogObjectHolder.me().set(student);
        return PREFIX + "student_edit.html";
    }

    /**
     * 获取学生列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<Student> entityWrapper = new EntityWrapper<>();
        Wrapper<Student> wrapper = entityWrapper.notIn("status", ManagerStatus.DELETED.getCode())
                .like("name", condition);
        List<Map<String, Object>> maps = studentService.selectMaps(wrapper);
        return super.warpObject(new StudentWrapper(maps));
    }

    /**
     * 新增学生
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Student student) {
        student.setCreatetime(new Date());
        studentService.insert(student);
        return SUCCESS_TIP;
    }

    /**
     * 删除学生
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer studentId) {
        Student student = studentService.selectById(studentId);
        student.setStatus(ManagerStatus.DELETED.getCode());
        studentService.updateById(student);
        return SUCCESS_TIP;
    }

    /**
     * 修改学生
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Student student) {
        studentService.updateById(student);
        return SUCCESS_TIP;
    }

    /**
     * 学生详情
     */
    @RequestMapping(value = "/detail/{studentId}")
    @ResponseBody
    public Object detail(@PathVariable("studentId") Integer studentId) {
        return studentService.selectById(studentId);
    }
}
