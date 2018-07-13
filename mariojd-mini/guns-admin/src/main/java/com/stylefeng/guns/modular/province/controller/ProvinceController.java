package com.stylefeng.guns.modular.province.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.province.service.IProvinceService;
import com.stylefeng.guns.modular.system.model.Province;
import com.stylefeng.guns.modular.system.warpper.ProvinceWrapper;
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
 * 省份控制器
 *
 * @author fengshuonan
 * @Date 2018-04-05 17:44:52
 */
@Controller
@RequestMapping("/province")
public class ProvinceController extends BaseController {

    private String PREFIX = "/province/";

    @Autowired
    private IProvinceService provinceService;

    /**
     * 跳转到省份首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "province.html";
    }

    /**
     * 跳转到添加省份
     */
    @RequestMapping("/province_add")
    public String provinceAdd() {
        return PREFIX + "province_add.html";
    }

    /**
     * 跳转到修改省份
     */
    @RequestMapping("/province_update/{provinceId}")
    public String provinceUpdate(@PathVariable Integer provinceId, Model model) {
        Province province = provinceService.selectById(provinceId);
        model.addAttribute("item", province);
        LogObjectHolder.me().set(province);
        return PREFIX + "province_edit.html";
    }

    /**
     * 获取省份列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        EntityWrapper<Province> entityWrapper = new EntityWrapper<>();
        Wrapper<Province> wrapper = entityWrapper
                .notIn("status", ManagerStatus.DELETED.getCode())
                .like("name", condition);
        List<Map<String, Object>> maps = provinceService.selectMaps(wrapper);
        return super.warpObject(new ProvinceWrapper(maps));
    }

    /**
     * 新增省份
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Province province) {
        province.setCreatetime(new Date());
        provinceService.insert(province);
        return SUCCESS_TIP;
    }

    /**
     * 删除省份
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer provinceId) {
        Province province = provinceService.selectById(provinceId);
        province.setStatus(ManagerStatus.DELETED.getCode());
        provinceService.updateById(province);
        return SUCCESS_TIP;
    }

    /**
     * 修改省份
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Province province) {
        provinceService.updateById(province);
        return SUCCESS_TIP;
    }

    /**
     * 省份详情
     */
    @RequestMapping(value = "/detail/{provinceId}")
    @ResponseBody
    public Object detail(@PathVariable("provinceId") Integer provinceId) {
        return provinceService.selectById(provinceId);
    }
}
