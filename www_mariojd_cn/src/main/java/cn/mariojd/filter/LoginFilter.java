package cn.mariojd.filter;

import cn.mariojd.entity.User;
import cn.mariojd.service.UserService;
import cn.mariojd.util.Md5DigestUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录认证过滤器
 * Created by Mario
 */
public class LoginFilter extends OncePerRequestFilter {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            // session范围的user不存在，继续往下执行查询cookie信息
            Cookie[] cookies = request.getCookies();
            if (null != cookies) {
                String username = "";
                String password = "";
                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName())) {
                        username = cookie.getValue();
                    }
                    if ("password".equals(cookie.getName())) {
                        password = cookie.getValue();
                    }
                }
                if (!"".equals(username) && !"".equals(password)) {
                    // cookie信息存在，校验cookie所对应的用户信息
                    User user = userService.getUserByUsername(username);
                    if (null != user) {
                        String psw = Md5DigestUtil.getMd5(password + user.getSalt());
                        if (psw.equals(user.getPassword())) {
                            session.setAttribute("user", user);
                        }
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
