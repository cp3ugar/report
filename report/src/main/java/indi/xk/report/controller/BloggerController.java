package indi.xk.report.controller;

import indi.xk.report.pojo.Blogger;
import indi.xk.report.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author xk
 * @Date 2020/8/20 17:55
 * @Version 1.0
 */
@Controller
public class BloggerController {
    @Autowired
    private BloggerService bloggerService;

    @PostMapping("/blogRegister")
    @ResponseBody
    private String register(Blogger blogger){
        bloggerService.register(blogger);
        return "注册成功";
    }

    @GetMapping("/blogLogin")
    private String login(Blogger blogger){
        System.out.println("登录");
        try {
            bloggerService.login(blogger);
        }catch (Exception e){
            return "toBlogger";
        }
        return "jsp/myBlog";
    }

    @RequestMapping("/toBlogger")
    private String toLogin(){
        return "jsp/blogger";
    }
}
