package com.stylefeng.guns.modular.attendence.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Attendence;
import com.stylefeng.guns.modular.attendence.service.IAttendenceService;

/**
 * 考勤控制器
 *
 * @author fengshuonan
 * @Date 2018-05-10 00:47:22
 */
@Controller
@RequestMapping("/attendence")
public class AttendenceController extends BaseController {

    private String PREFIX = "/attendence/attendence/";

    @Autowired
    private IAttendenceService attendenceService;

    /**
     * 跳转到考勤首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "attendence.html";
    }

    /**
     * 跳转到添加考勤
     */
    @RequestMapping("/attendence_add")
    public String attendenceAdd() {
        return PREFIX + "attendence_add.html";
    }

    /**
     * 跳转到修改考勤
     */
    @RequestMapping("/attendence_update/{attendenceId}")
    public String attendenceUpdate(@PathVariable Integer attendenceId, Model model) {
        Attendence attendence = attendenceService.selectById(attendenceId);
        model.addAttribute("item",attendence);
        LogObjectHolder.me().set(attendence);
        return PREFIX + "attendence_edit.html";
    }

    /**
     * 获取考勤列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return attendenceService.selectList(null);
    }

    /**
     * 新增考勤
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Attendence attendence) {
        attendenceService.insert(attendence);
        return SUCCESS_TIP;
    }

    /**
     * 删除考勤
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer attendenceId) {
        attendenceService.deleteById(attendenceId);
        return SUCCESS_TIP;
    }

    /**
     * 修改考勤
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Attendence attendence) {
        attendenceService.updateById(attendence);
        return SUCCESS_TIP;
    }

    /**
     * 考勤详情
     */
    @RequestMapping(value = "/detail/{attendenceId}")
    @ResponseBody
    public Object detail(@PathVariable("attendenceId") Integer attendenceId) {
        return attendenceService.selectById(attendenceId);
    }
}
