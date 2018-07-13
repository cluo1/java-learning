package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.Read;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mario
 */
@Controller
@RequestMapping("/read")
public class ReadController extends BaseController {

    @PutMapping("/{rid}")
    @ResponseBody
    public MessageResult update(@ModelAttribute Read read) {
        return readService.update(read);
    }

    @GetMapping("/{rid}")
    public String edit(@PathVariable Integer rid, Model model) {
        Read read = readService.getRead(rid);
        model.addAttribute(read);
        return "page/read_edit";
    }

    @PostMapping
    @ResponseBody
    public void save(@ModelAttribute Read read) {
        readService.save(read);
    }

    @GetMapping("/edit")
    public String add() {
        return "page/read_edit";
    }

    @DeleteMapping("/{rid}")
    public String delete(@PathVariable Integer rid, Integer index, Integer pageNumber) {
        return readService.delete(rid, index, pageNumber);
    }

    @ResponseBody
    @RequestMapping(value = "/list/{pageNumber}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public Page<Read> list(@PathVariable Integer pageNumber) {
        return readService.findAll(pageNumber);
    }

    @GetMapping("/list")
    public String page() {
        return "page/read";
    }
}
