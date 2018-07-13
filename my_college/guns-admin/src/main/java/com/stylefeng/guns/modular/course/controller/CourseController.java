package com.stylefeng.guns.modular.course.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.classes.service.IClassesService;
import com.stylefeng.guns.modular.course.service.ICourseService;
import com.stylefeng.guns.modular.system.model.Course;
import com.stylefeng.guns.modular.system.service.IDictService;
import com.stylefeng.guns.modular.system.transfer.CourseVO;
import com.stylefeng.guns.modular.system.warpper.CourseWrapper;
import com.stylefeng.guns.modular.teacher.service.ITeacherService;
import org.springframework.beans.BeanUtils;
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
 * 课程控制器
 *
 * @author fengshuonan
 * @Date 2018-04-06 22:32:06
 */
@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {

    private String PREFIX = "/course/";

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IClassesService classesService;

    @Autowired
    private IDictService dictService;

    /**
     * 跳转到课程首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "course.html";
    }

    /**
     * 跳转到添加课程
     */
    @RequestMapping("/course_add")
    public String courseAdd(Model model) {
        model.addAttribute("teachers", teacherService.findList());
        model.addAttribute("classes", classesService.findList());
        //父字典id
        model.addAttribute("dictList", dictService.findByPid(53));
        return PREFIX + "course_add.html";
    }

    /**
     * 跳转到修改课程
     */
    @RequestMapping("/course_update/{courseId}")
    public String courseUpdate(@PathVariable Integer courseId, Model model) {
        Course course = courseService.selectById(courseId);
        CourseVO courseVO = new CourseVO();
        BeanUtils.copyProperties(course, courseVO);
        courseVO.setEndTime(DateUtil.format(course.getEndTime(), "HH:mm"));
        courseVO.setStartTime(DateUtil.format(course.getStartTime(), "HH:mm"));
        model.addAttribute("item", courseVO);
        model.addAttribute("teachers", teacherService.findList());
        model.addAttribute("classes", classesService.findList());
        //父字典id
        model.addAttribute("dictList", dictService.findByPid(53));
        LogObjectHolder.me().set(course);
        return PREFIX + "course_edit.html";
    }

    /**
     * 获取课程列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<Course> entityWrapper = new EntityWrapper<>();
        Wrapper<Course> wrapper = entityWrapper.notIn("status", ManagerStatus.DELETED.getCode())
                .like("name", condition);
        List<Map<String, Object>> maps = courseService.selectMaps(wrapper);
        return super.warpObject(new CourseWrapper(maps));
    }

    /**
     * 新增课程
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Course course) {
        course.setCreatetime(new Date());
        course.setUid(ShiroKit.getUser().id);
        courseService.insert(course);
        return SUCCESS_TIP;
    }

    /**
     * 删除课程
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer courseId) {
        Course course = courseService.selectById(courseId);
        course.setStatus(ManagerStatus.DELETED.getCode());
        courseService.updateById(course);
        return SUCCESS_TIP;
    }

    /**
     * 修改课程
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Course course) {
        courseService.updateById(course);
        return SUCCESS_TIP;
    }

    /**
     * 课程详情
     */
    @RequestMapping(value = "/detail/{courseId}")
    @ResponseBody
    public Object detail(@PathVariable("courseId") Integer courseId) {
        return courseService.selectById(courseId);
    }
}
