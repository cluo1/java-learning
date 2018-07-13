package cn.mariojd;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.Admin;
import cn.mariojd.enums.MessageEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Controller
@EnableCaching
public class HappymarioApplication extends BaseController {

    @GetMapping("/reports")
    @ResponseBody
    public MessageResult reports() {
        Map<String, Object> report = new HashMap<>();
        report.put("user_report", userService.report());
        report.put("message_report", messageService.report());
        report.put("seckill_report",seckillService.report());
        report.put("order_report",orderService.report());
        return new MessageResult(true, report);
    }

    @GetMapping("/report")
    public String report() {
        return "page/report";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return "redirect:/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public MessageResult login(String account, String password, HttpServletRequest request) {
        Admin admin = adminService.login(account, password);
        if (admin == null) {
            return new MessageResult(MessageEnum.ADMIN_LOGINFAILURE);
        } else {
            request.getSession().setAttribute("admin", admin);
            return new MessageResult(MessageEnum.ADMIN_LOGINSUCCESS);
        }
    }

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            return "redirect:/login";
        } else {
            request.getSession().setAttribute("admin", admin);
            return "index";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(HappymarioApplication.class, args);
    }
}
