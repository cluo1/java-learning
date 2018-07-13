package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mario
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @PutMapping("/{uid}")
    @ResponseBody
    public MessageResult update(@ModelAttribute User user,
                                String account, Integer pageNumber) {
        if (account == null) {
            return userService.update(user);
        } else {
            return userService.operation(user, account, pageNumber);
        }
    }


    @GetMapping("/{uid}")
    public String edit(@PathVariable Integer uid, Model model) {
        User user = userService.getUser(uid);
        model.addAttribute("user", user);
        return "page/user_edit";
    }

    @PostMapping
    @ResponseBody
    public void save(@ModelAttribute User user) {
        userService.save(user);
    }

    @GetMapping("/edit")
    public String add() {
        return "page/user_edit";
    }

    @DeleteMapping("/{uid}")
    public String delete(@PathVariable Integer uid, Integer index, Integer pageNumber) {
        return userService.delete(uid, index, pageNumber);
    }

    @ResponseBody
    @RequestMapping(value = "/list/{pageNumber}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public Page<User> list(@PathVariable Integer pageNumber) {
        return userService.findAll(pageNumber);
    }

    @GetMapping("/list")
    public String page() {
        return "page/user";
    }


}
