package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mario
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @PutMapping("/{aid}")
    @ResponseBody
    public MessageResult update(@ModelAttribute Admin admin,
                                HttpServletRequest request) {
        Admin sessionAdmin = (Admin) request.getSession().getAttribute("admin");
        return adminService.update(admin, sessionAdmin);
    }

    @GetMapping("/{aid}")
    public String page() {
        return "page/admin";
    }

}
