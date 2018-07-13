package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.Seckill;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mario
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController extends BaseController {

    @PutMapping("/{sid}")
    @ResponseBody
    public MessageResult update(@ModelAttribute Seckill seckill) {
        return seckillService.update(seckill);
    }

    @GetMapping("/{sid}")
    public String edit(@PathVariable Integer sid, Model model) {
        Seckill seckill = seckillService.getSeckill(sid);
        model.addAttribute(seckill);
        return "page/seckill_edit";
    }

    @PostMapping
    @ResponseBody
    public void save(@ModelAttribute Seckill seckill) {
        seckillService.save(seckill);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/edit")
    public String add() {
        return "page/seckill_edit";
    }

    @DeleteMapping("/{sid}")
    public String delete(@PathVariable Integer sid, Integer index, Integer pageNumber) {
        return seckillService.delete(sid, index, pageNumber);
    }

    @ResponseBody
    @RequestMapping(value = "/list/{pageNumber}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public Page<Seckill> list(@PathVariable Integer pageNumber) {
        return seckillService.findAll(pageNumber);
    }

    @GetMapping("/list")
    public String page() {
        return "page/seckill";
    }
}
