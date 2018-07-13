package com.stylefeng.guns.modular.school.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.province.service.IProvinceService;
import com.stylefeng.guns.modular.school.service.ISchoolService;
import com.stylefeng.guns.modular.system.model.School;
import com.stylefeng.guns.modular.system.warpper.SchoolWrapper;
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
 * 学校控制器
 *
 * @author fengshuonan
 * @Date 2018-04-05 20:49:08
 */
@Controller
@RequestMapping("/school")
public class SchoolController extends BaseController {

    private String PREFIX = "/school/";

    @Autowired
    private ISchoolService schoolService;

    @Autowired
    private IProvinceService provinceService;

    /**
     * 跳转到学校首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "school.html";
    }

    /**
     * 跳转到添加学校
     */
    @RequestMapping("/school_add")
    public String schoolAdd(Model model) {
        model.addAttribute("provinces", provinceService.findList());
        return PREFIX + "school_add.html";
    }

    /**
     * 跳转到修改学校
     */
    @RequestMapping("/school_update/{schoolId}")
    public String schoolUpdate(@PathVariable Integer schoolId, Model model) {
        School school = schoolService.selectById(schoolId);
        model.addAttribute("item", school);
        model.addAttribute("provinces", provinceService.findList());

        LogObjectHolder.me().set(school);
        return PREFIX + "school_edit.html";
    }

    /**
     * 获取学校列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<School> entityWrapper = new EntityWrapper<>();
        Wrapper<School> wrapper = entityWrapper.notIn("status", ManagerStatus.DELETED.getCode())
                .like("full_name", condition);
        List<Map<String, Object>> maps = schoolService.selectMaps(wrapper);
        return super.warpObject(new SchoolWrapper(maps));
    }

    /**
     * 新增学校
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(School school) {
        school.setCreatetime(new Date());
        schoolService.insert(school);
        return SUCCESS_TIP;
    }

    /**
     * 删除学校
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer schoolId) {
        School school = schoolService.selectById(schoolId);
        school.setStatus(ManagerStatus.DELETED.getCode());
        schoolService.updateById(school);
        return SUCCESS_TIP;
    }

    /**
     * 修改学校
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(School school) {
        schoolService.updateById(school);
        return SUCCESS_TIP;
    }

    /**
     * 学校详情
     */
    @RequestMapping(value = "/detail/{schoolId}")
    @ResponseBody
    public Object detail(@PathVariable("schoolId") Integer schoolId) {
        return schoolService.selectById(schoolId);
    }
}
