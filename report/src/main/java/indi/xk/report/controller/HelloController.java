package indi.xk.report.controller;

import indi.xk.report.properties.StudentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;

/**
 * @Author xk
 * @Date 2019/12/17 10:16
 * @Version 1.0
 */
@Controller
public class HelloController {
    @Autowired
    private StudentProperties studentProperties;

//    @RequestMapping("/hello")
//    public String hello() {
//        return studentProperties.getName()+studentProperties.getAge()+"岁！";
//    }

    @RequestMapping("/hello")
    public String hello(Model m) {
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
        return "hello";
    }
}
