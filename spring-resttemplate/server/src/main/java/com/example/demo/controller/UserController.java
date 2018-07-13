package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jdq
 * @date 2018/4/3 17:48
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * id + user
     */
    private Map<Long, User> userMap = new HashMap<>();

    private AtomicLong userId = new AtomicLong(0);

    /**
     * 通过id获得用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public User getUserById(@PathVariable long id, HttpServletRequest request) {
        System.out.println("request = [" + request.getHeader(HttpHeaders.CONTENT_TYPE) + "]");
        if (userMap.containsKey(id)) {
            return userMap.get(id);
        }
        return null;
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @PostMapping
    public User addUser(@RequestBody @Valid User user, HttpServletRequest request) {
        System.out.println("request = [" + request.getHeader(HttpHeaders.CONTENT_TYPE) + "]");
        user.setId(userId.getAndIncrement());
        userMap.put(user.getId(), user);
        System.out.println("userMap = [" + userMap.toString() + "]");
        return user;
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    @PutMapping("{id}")
    public User updateUserById(@PathVariable long id,
                               @RequestBody @Valid User user,
                               HttpServletRequest request) {
        System.out.println("request = [" + request.getHeader(HttpHeaders.CONTENT_TYPE) + "]");
        if (userMap.containsKey(id)) {
            userMap.put(id, user);
        }
        System.out.println("userMap = [" + userMap.toString() + "]");
        return user;
    }

    /**
     * 删除用户信息
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable long id, HttpServletRequest request) {
        System.out.println("request = [" + request.getHeader(HttpHeaders.CONTENT_TYPE) + "]");
        userMap.remove(id);
        System.out.println("userMap = [" + userMap.toString() + "]");
    }

}
