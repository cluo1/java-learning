package com.stylefeng.guns.modular.attendenceDetail.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.AttendenceDetail;
import com.stylefeng.guns.modular.attendenceDetail.service.IAttendenceDetailService;

/**
 * 考勤明细控制器
 *
 * @author fengshuonan
 * @Date 2018-05-10 00:56:12
 */
@Controller
@RequestMapping("/attendenceDetail")
public class AttendenceDetailController extends BaseController {

    private String PREFIX = "/attendenceDetail/attendenceDetail/";

    @Autowired
    private IAttendenceDetailService attendenceDetailService;

    /**
     * 跳转到考勤明细首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "attendenceDetail.html";
    }

    /**
     * 跳转到添加考勤明细
     */
    @RequestMapping("/attendenceDetail_add")
    public String attendenceDetailAdd() {
        return PREFIX + "attendenceDetail_add.html";
    }

    /**
     * 跳转到修改考勤明细
     */
    @RequestMapping("/attendenceDetail_update/{attendenceDetailId}")
    public String attendenceDetailUpdate(@PathVariable Integer attendenceDetailId, Model model) {
        AttendenceDetail attendenceDetail = attendenceDetailService.selectById(attendenceDetailId);
        model.addAttribute("item",attendenceDetail);
        LogObjectHolder.me().set(attendenceDetail);
        return PREFIX + "attendenceDetail_edit.html";
    }

    /**
     * 获取考勤明细列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return attendenceDetailService.selectList(null);
    }

    /**
     * 新增考勤明细
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AttendenceDetail attendenceDetail) {
        attendenceDetailService.insert(attendenceDetail);
        return SUCCESS_TIP;
    }

    /**
     * 删除考勤明细
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer attendenceDetailId) {
        attendenceDetailService.deleteById(attendenceDetailId);
        return SUCCESS_TIP;
    }

    /**
     * 修改考勤明细
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AttendenceDetail attendenceDetail) {
        attendenceDetailService.updateById(attendenceDetail);
        return SUCCESS_TIP;
    }

    /**
     * 考勤明细详情
     */
    @RequestMapping(value = "/detail/{attendenceDetailId}")
    @ResponseBody
    public Object detail(@PathVariable("attendenceDetailId") Integer attendenceDetailId) {
        return attendenceDetailService.selectById(attendenceDetailId);
    }
}
