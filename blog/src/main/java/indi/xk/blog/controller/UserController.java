package indi.xk.blog.controller;

import indi.xk.blog.bean.User;
import indi.xk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xk
 * @Date 2020/8/20 17:55
 * @Version 1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    private String register(User user){
        userService.register(user);
        return "登录成功";
    }

    @GetMapping("/users")
    private String login(User user){
        try {
            userService.login(user);
        }catch (Exception e){
            return "账号密码不正确";
        }
        return "登录成功";
    }
}
