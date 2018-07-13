package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.User;
import cn.mariojd.enums.MessageEnum;
import cn.mariojd.util.Md5DigestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Mario
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @PutMapping("/{uid}/nickname")
    @ResponseBody
    public String updateNicknameByUid(@PathVariable Integer uid, String nickname) {
        userService.updateNicknameByUid(uid, nickname);
        return nickname;
    }

    @PutMapping("/{uid}/username")
    @ResponseBody
    public void updateUsernameByUid(@PathVariable Integer uid, @RequestParam("username") String username) {
        userService.updateUsernameByUid(uid, username);
    }

    @GetMapping("/{uid}/code")
    @ResponseBody
    public MessageResult checkAndUpdateCode(@PathVariable Integer uid, String code) {
        return userService.checkCodeByUid(uid, code);
    }

    @PutMapping("/{uid}/code")
    @ResponseBody
    public void updateCodeByUid(@PathVariable Integer uid, String username) {
        userService.updateCodeByUid(uid, username);
    }

    @PutMapping(value = "/{uid}/icon")
    @ResponseBody
    public String saveIcon(@PathVariable Integer uid, String icon, HttpServletRequest request) {
        String iconPath = icon
                .replaceAll(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort(), "");
        userService.saveIconByUid(uid, iconPath);
        return icon;
    }

    @GetMapping("/logout")
    public String logout(@RequestParam(required = false, defaultValue = "0") String index,
                         HttpServletRequest request, HttpServletResponse response) {
        Cookie usernameCookie = new Cookie("username", null);
        Cookie passwordCookie = new Cookie("password", null);
        usernameCookie.setMaxAge(0);
        passwordCookie.setMaxAge(0);
        usernameCookie.setPath("/");
        passwordCookie.setPath("/");
        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);
        request.getSession().removeAttribute("user");
        System.out.println(index);
        if (index.equals("1")) {
            return "redirect:/seckill";
        } else if (index.equals("2")) {
            return "redirect:/message";
        }
        return "redirect:/";
    }


    @PostMapping("/login")
    @ResponseBody
    public MessageResult login(String username, String password,
                               @RequestParam(required = false) String rememberme, HttpServletRequest request,
                               HttpServletResponse response) {
        User user = userService.getUserByUsername(username);
        if (user == null || user.getState().equals(0)) {
            return new MessageResult(MessageEnum.LOGIN_ERROR);
        } else if (user.getState().equals(2)) {
            return new MessageResult(MessageEnum.LOGIN_DISABLED);
        } else {
            String psw = Md5DigestUtil.getMd5(Md5DigestUtil.getMd5(password) + user.getSalt());
            if (!psw.equals(user.getPassword())) {
                return new MessageResult(MessageEnum.LOGIN_ERROR);
            } else {
                if (rememberme != null && rememberme.equals("1")) {
                    Cookie usernameCookie = new Cookie("username", username);
                    Cookie passwordCookie = new Cookie("password", Md5DigestUtil.getMd5(password));
                    usernameCookie.setMaxAge(60 * 60 * 24);
                    passwordCookie.setMaxAge(60 * 60 * 24);
                    usernameCookie.setPath("/");
                    passwordCookie.setPath("/");
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                }
                request.getSession().setAttribute("user", user);
                return new MessageResult(MessageEnum.LOGIN_SUCCESS);
            }
        }
    }

    @PutMapping("/forgot")
    @ResponseBody
    public void updatePswByUsername(String username, String password) {
        userService.updatePswByUsername(username, password);
    }

    @GetMapping("/forgot")
    @ResponseBody
    public MessageResult checkCode(String username, String code) {
        return userService.checkUserByUsernameAndCode(username, code);
    }

    @PostMapping("/forgot")
    @ResponseBody
    public void sendCode(String username) {
        // 手机|邮箱找回发送并更新code
        userService.updateCodeByUsername(username);
    }

    @GetMapping("/{page}")
    public String page(@PathVariable String page, HttpServletRequest request) {
        String requestPath = request.getServletPath();
        if (requestPath.equals("/user/setting") && request.getSession().getAttribute("user") == null) {
            return "redirect:/";
        }
        return "user/" + page;
    }

    @PostMapping("/activation/{telephone}")
    @ResponseBody
    public MessageResult activeByTelphone(@PathVariable String telephone, String code, String password) {
        MessageResult messageResult = userService.getUserByTelAndCode(telephone, code, password);
        return messageResult;
    }

    @GetMapping("/activation/{email}/{code}")
    public String activeByEmail(@PathVariable String email, @PathVariable String code,
                                Map<String, Object> map) {
        MessageResult messageResult = userService.activeByEmail(email, code);
        map.put("messageResult", messageResult);
        return "user/msg";
    }

    @PutMapping
    @ResponseBody
    public void updateUser(String username, String password) {
        userService.updatePswAndCode(username, password);
    }

    @PostMapping
    @ResponseBody
    public void saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
    }

    @GetMapping("/state")
    @ResponseBody
    public MessageResult getUser(String username) {
        return userService.checkStateByUsername(username);
    }

}
