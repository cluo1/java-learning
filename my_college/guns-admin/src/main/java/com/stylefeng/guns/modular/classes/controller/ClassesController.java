package com.stylefeng.guns.modular.classes.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.modular.school.service.ISchoolService;
import com.stylefeng.guns.modular.system.warpper.ClassesWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Classes;
import com.stylefeng.guns.modular.classes.service.IClassesService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 班级控制器
 *
 * @author fengshuonan
 * @Date 2018-04-06 09:46:04
 */
@Controller
@RequestMapping("/classes")
public class ClassesController extends BaseController {

    private String PREFIX = "/classes/";

    @Autowired
    private IClassesService classesService;

    @Autowired
    private ISchoolService schoolService;

    /**
     * 跳转到班级首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "classes.html";
    }

    /**
     * 跳转到添加班级
     */
    @RequestMapping("/classes_add")
    public String classesAdd(Model model) {
        model.addAttribute("schools", schoolService.findList());
        return PREFIX + "classes_add.html";
    }

    /**
     * 跳转到修改班级
     */
    @RequestMapping("/classes_update/{classesId}")
    public String classesUpdate(@PathVariable Integer classesId, Model model) {
        Classes classes = classesService.selectById(classesId);
        model.addAttribute("item", classes);
        model.addAttribute("schools", schoolService.findList());

        LogObjectHolder.me().set(classes);
        return PREFIX + "classes_edit.html";
    }

    /**
     * 获取班级列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<Classes> entityWrapper = new EntityWrapper<>();
        Wrapper<Classes> wrapper = entityWrapper.notIn("status", ManagerStatus.DELETED.getCode())
                .like("full_name", condition);
        List<Map<String, Object>> maps = classesService.selectMaps(wrapper);
        return super.warpObject(new ClassesWrapper(maps));
    }

    /**
     * 新增班级
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Classes classes) {
        classes.setCreatetime(new Date());
        classesService.insert(classes);
        return SUCCESS_TIP;
    }

    /**
     * 删除班级
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer classesId) {
        Classes classes = classesService.selectById(classesId);
        classes.setStatus(ManagerStatus.DELETED.getCode());
        classesService.updateById(classes);
        return SUCCESS_TIP;
    }

    /**
     * 修改班级
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Classes classes) {
        classesService.updateById(classes);
        return SUCCESS_TIP;
    }

    /**
     * 班级详情
     */
    @RequestMapping(value = "/detail/{classesId}")
    @ResponseBody
    public Object detail(@PathVariable("classesId") Integer classesId) {
        return classesService.selectById(classesId);
    }
}
