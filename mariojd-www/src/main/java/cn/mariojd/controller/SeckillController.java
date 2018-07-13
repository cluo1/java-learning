package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.PageResult;
import cn.mariojd.dto.SeckillExecution;
import cn.mariojd.dto.SeckillExposure;
import cn.mariojd.dto.SeckillResult;
import cn.mariojd.entity.Order;
import cn.mariojd.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Mario
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(SeckillController.class);

    @PostMapping("/{sid}/{md5}/execution")
    @ResponseBody
    public SeckillResult<SeckillExecution> execution(@PathVariable Integer sid,
                                                     @PathVariable String md5,
                                                     @ModelAttribute Order order) {
        SeckillResult<SeckillExecution> result;
        if (order.getUid() == null) {
            result = new SeckillResult<SeckillExecution>(false, "尚未登录");
        } else {
            //执行带存储过程的抢购
            SeckillExecution seckillExecution = seckillService.executeSeckillByProcedure(sid, md5, order);
            result = new SeckillResult<SeckillExecution>(true, seckillExecution);
        }
        return result;
    }

    @PostMapping("/{sid}/exposure")
    @ResponseBody
    public SeckillResult<SeckillExposure> exposure(@PathVariable Integer sid) {
        SeckillResult<SeckillExposure> result;
        try {
            SeckillExposure exposer = seckillService.exportSeckillUrl(sid);
            result = new SeckillResult<SeckillExposure>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<SeckillExposure>(false, e.getMessage());
        }
        return result;
    }

    @GetMapping("/{sid}")
    public String detail(@PathVariable Integer sid, Model model) {
        if (sid == null) {
            return "redirect:/seckill";
        } else {
            Seckill seckill = seckillService.getSeckillBySid(sid);
            if (seckill == null) {
                return "redirect:/seckill";
            } else {
                model.addAttribute("seckill", seckill);
                return "seckill/detail";
            }
        }
    }

    @GetMapping
    public String list(Model model) {
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("seckillList", list);
        return "seckill/list";
    }

}
