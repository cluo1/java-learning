package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.Order;
import cn.mariojd.entity.Seckill;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Mario
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    @PutMapping("/{oid}")
    @ResponseBody
    public MessageResult operation(@PathVariable String oid, Integer state) {
        return orderService.update(oid, state);
    }

    @ResponseBody
    @GetMapping("/page")
    public Page<Order> list(String pageNumber) {
        return orderService.findAll(Integer.parseInt(pageNumber));
    }

    @GetMapping("/list")
    public String page() {
        return "page/order";
    }
}
