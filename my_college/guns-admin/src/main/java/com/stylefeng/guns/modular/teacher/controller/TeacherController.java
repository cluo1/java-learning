package com.stylefeng.guns.modular.teacher.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.school.service.ISchoolService;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.modular.system.warpper.TeacherWrapper;
import com.stylefeng.guns.modular.teacher.service.ITeacherService;
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
 * 教师控制器
 *
 * @author fengshuonan
 * @Date 2018-04-06 10:50:11
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    private String PREFIX = "/teacher/";

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ISchoolService schoolService;

    /**
     * 跳转到教师首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "teacher.html";
    }

    /**
     * 跳转到添加教师
     */
    @RequestMapping("/teacher_add")
    public String teacherAdd(Model model) {
        model.addAttribute("schools", schoolService.findList());
        return PREFIX + "teacher_add.html";
    }

    /**
     * 跳转到修改教师
     */
    @RequestMapping("/teacher_update/{teacherId}")
    public String teacherUpdate(@PathVariable Integer teacherId, Model model) {
        Teacher teacher = teacherService.selectById(teacherId);
        model.addAttribute("item", teacher);
        model.addAttribute("schools", schoolService.findList());
        LogObjectHolder.me().set(teacher);
        return PREFIX + "teacher_edit.html";
    }

    /**
     * 获取教师列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<Teacher> entityWrapper = new EntityWrapper<>();
        Wrapper<Teacher> wrapper = entityWrapper.notIn("status", ManagerStatus.DELETED.getCode())
                .like("name", condition);
        List<Map<String, Object>> maps = teacherService.selectMaps(wrapper);
        return super.warpObject(new TeacherWrapper(maps));
    }

    /**
     * 新增教师
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Teacher teacher) {
        teacher.setCreatetime(new Date());
        teacherService.insert(teacher);
        return SUCCESS_TIP;
    }

    /**
     * 删除教师
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer teacherId) {
        Teacher teacher = teacherService.selectById(teacherId);
        teacher.setStatus(ManagerStatus.DELETED.getCode());
        teacherService.updateById(teacher);
        return SUCCESS_TIP;
    }

    /**
     * 修改教师
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Teacher teacher) {
        teacherService.updateById(teacher);
        return SUCCESS_TIP;
    }

    /**
     * 教师详情
     */
    @RequestMapping(value = "/detail/{teacherId}")
    @ResponseBody
    public Object detail(@PathVariable("teacherId") Integer teacherId) {
        return teacherService.selectById(teacherId);
    }
}
