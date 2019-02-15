package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * Created by Mario
 */
@Controller
public class ReadController extends BaseController {

    @GetMapping("/reads/{currentPage}")
    @ResponseBody
    public Map<String, Object> list(@PathVariable Integer currentPage) {
        PageResult page = new PageResult();
        Pattern pattern = compile("[0-9]{1,9}");
        if (currentPage == null || !pattern.matcher(String.valueOf(currentPage)).matches()) {
            page.setCurrentPage(1);
        } else {
            page.setCurrentPage(Integer.valueOf(currentPage));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("readList", readService.getList(page));
        map.put("page", page.getPageResult(page));
        return map;
    }

}
