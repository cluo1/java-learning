package com.stylefeng.guns.modular.miniuser.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.classes.service.IClassesService;
import com.stylefeng.guns.modular.miniuser.service.IMiniUserService;
import com.stylefeng.guns.modular.system.model.MiniUser;
import com.stylefeng.guns.modular.system.service.IDictService;
import com.stylefeng.guns.modular.system.warpper.MiniUserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 *
 * @author fengshuonan
 * @Date 2018-04-07 16:58:56
 */
@Controller
@RequestMapping("/miniUser")
public class MiniUserController extends BaseController {

    private String PREFIX = "/miniuser/";

    @Autowired
    private IMiniUserService miniUserService;

    @Autowired
    private IClassesService classesService;

    @Autowired
    private IDictService dictService;

    /**
     * 跳转到用户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "miniUser.html";
    }

    /**
     * 跳转到添加用户
     */
    /*@RequestMapping("/miniUser_add")
    @Deprecated
    public String miniUserAdd() {
        return PREFIX + "miniUser_add.html";
    }*/

    /**
     * 跳转到修改用户
     */
    @RequestMapping("/miniUser_update/{miniUserId}")
    public String miniUserUpdate(@PathVariable Integer miniUserId, Model model) {
        MiniUser miniUser = miniUserService.selectById(miniUserId);
        model.addAttribute("item", miniUser);
        model.addAttribute("classes", classesService.findList());
        //学生角色，不可修改老师角色
        model.addAttribute("dictList", dictService.findByPid(50));
        LogObjectHolder.me().set(miniUser);
        return PREFIX + "miniUser_edit.html";
    }

    /**
     * 获取用户列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<MiniUser> entityWrapper = new EntityWrapper<>();
        Wrapper<MiniUser> wrapper = entityWrapper
                .like("realName", condition)
                .or()
                .like("studentId", condition);
        List<Map<String, Object>> maps = miniUserService.selectMaps(wrapper);
        return super.warpObject(new MiniUserWrapper(maps));
    }

    /**
     * 新增用户
     */
    /*@RequestMapping(value = "/add")
    @ResponseBody
    @Deprecated
    public Object add(MiniUser miniUser) {
        miniUserService.insert(miniUser);
        return SUCCESS_TIP;
    }*/

    /**
     * 删除用户
     */
    /*@RequestMapping(value = "/delete")
    @ResponseBody
    @Deprecated
    public Object delete(@RequestParam Integer miniUserId) {
        miniUserService.deleteById(miniUserId);
        return SUCCESS_TIP;
    }*/

    /**
     * 修改用户
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MiniUser miniUser) {
        miniUserService.updateById(miniUser);
        return SUCCESS_TIP;
    }

    /**
     * 用户详情
     */
    @RequestMapping(value = "/detail/{miniUserId}")
    @ResponseBody
    public Object detail(@PathVariable("miniUserId") Integer miniUserId) {
        return miniUserService.selectById(miniUserId);
    }
}
