package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mario
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    @PutMapping("/{nid}")
    @ResponseBody
    public void visit(@PathVariable Integer nid) {
        noticeService.updateVisit(nid);
    }
}
