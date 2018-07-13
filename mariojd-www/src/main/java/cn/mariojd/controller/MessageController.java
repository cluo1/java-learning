package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    @GetMapping("/list/{currentPage}")
    @ResponseBody
    public Map<String, Object> list(@PathVariable String currentPage) {
        PageResult page = new PageResult();
        Pattern pattern = Pattern.compile("[0-9]{1,9}");
        if (currentPage == null || !pattern.matcher(currentPage).matches()) {
            page.setCurrentPage(1);
        } else {
            page.setCurrentPage(Integer.valueOf(currentPage));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("messageList", messageService.getList(page));
        map.put("page", page.getPageResult(page));
        return map;
    }

    @PostMapping
    @ResponseBody
    public void save(Integer uid, String content) {
        userService.updateCount(uid);
        messageService.save(uid, content);
    }

    @GetMapping
    public String index(){
        return "user/index";
    }

}
