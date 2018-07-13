package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.PageResult;
import cn.mariojd.entity.Notice;
import net.sf.json.JSONObject;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Mario
 */
@Controller
public class IndexController extends BaseController {

    @GetMapping("/")
    public String index(Model model) {
        List<Notice> noticeList = noticeService.getTop5Notices();
        model.addAttribute(noticeList);
        return "index";
    }

}
