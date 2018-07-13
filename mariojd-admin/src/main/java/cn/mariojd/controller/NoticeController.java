package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mario
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    @PutMapping("/{nid}")
    @ResponseBody
    public MessageResult update(@ModelAttribute Notice notice) {
        return noticeService.update(notice);
    }

    @GetMapping("/{nid}")
    public String edit(@PathVariable Integer nid, Model model) {
        Notice notice = noticeService.getNotice(nid);
        model.addAttribute(notice);
        return "page/notice_edit";
    }

    @PostMapping
    @ResponseBody
    public void save(@ModelAttribute Notice notice) {
        noticeService.save(notice);
    }

    @GetMapping("/edit")
    public String add() {
        return "page/notice_edit";
    }

    @DeleteMapping("/{nid}")
    public String delete(@PathVariable Integer nid, Integer index, Integer pageNumber) {
        return noticeService.delete(nid, index, pageNumber);
    }

    @ResponseBody
    @RequestMapping(value = "/list/{pageNumber}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public Page<Notice> list(@PathVariable Integer pageNumber) {
        return noticeService.findAll(pageNumber);
    }

    @GetMapping("/list")
    public String page() {
        return "page/notice";
    }
}
