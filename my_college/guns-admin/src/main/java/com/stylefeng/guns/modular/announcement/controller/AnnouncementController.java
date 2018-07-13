package com.stylefeng.guns.modular.announcement.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.announcement.service.IAnnouncementService;
import com.stylefeng.guns.modular.classes.service.IClassesService;
import com.stylefeng.guns.modular.system.model.Announcement;
import com.stylefeng.guns.modular.system.warpper.AnnouncementWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知控制器
 *
 * @author fengshuonan
 * @Date 2018-04-06 17:30:04
 */
@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends BaseController {

    private String PREFIX = "/announcement/";

    @Autowired
    private IAnnouncementService announcementService;

    @Autowired
    private IClassesService classesService;

    /**
     * 跳转到通知首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "announcement.html";
    }

    /**
     * 跳转到添加通知
     */
    @RequestMapping("/announcement_add")
    public String announcementAdd(Model model) {
        model.addAttribute("classes", classesService.findList());
        return PREFIX + "announcement_add.html";
    }

    /**
     * 跳转到修改通知
     */
    @RequestMapping("/announcement_update/{announcementId}")
    public String announcementUpdate(@PathVariable Integer announcementId, Model model) {
        Announcement announcement = announcementService.selectById(announcementId);
        model.addAttribute("item", announcement);
        model.addAttribute("classes", classesService.findList());
        LogObjectHolder.me().set(announcement);
        return PREFIX + "announcement_edit.html";
    }

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<Announcement> entityWrapper = new EntityWrapper<>();
        Wrapper<Announcement> wrapper = entityWrapper.notIn("status", ManagerStatus.DELETED.getCode())
                .like("title", condition);
        List<Map<String, Object>> maps = announcementService.selectMaps(wrapper);
        return super.warpObject(new AnnouncementWrapper(maps));
    }

    /**
     * 新增通知
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Announcement announcement) throws UnsupportedEncodingException {
        announcement.setCreatetime(new Date());
        announcement.setContent(URLDecoder.decode(announcement.getContent(), "UTF-8"));
        announcementService.insert(announcement);
        return SUCCESS_TIP;
    }

    /**
     * 删除通知
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer announcementId) {
        Announcement announcement = announcementService.selectById(announcementId);
        announcement.setStatus(ManagerStatus.DELETED.getCode());
        announcementService.updateById(announcement);
        return SUCCESS_TIP;
    }

    /**
     * 修改通知
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Announcement announcement) throws UnsupportedEncodingException {
        announcement.setContent(URLDecoder.decode(announcement.getContent(), "UTF-8"));
        announcementService.updateById(announcement);
        return SUCCESS_TIP;
    }

    /**
     * 通知详情
     */
    @RequestMapping(value = "/detail/{announcementId}")
    @ResponseBody
    public Object detail(@PathVariable("announcementId") Integer announcementId) {
        return announcementService.selectById(announcementId);
    }
}
