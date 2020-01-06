package indi.xk.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.util.Date;

/**
 * @Author xk
 * @Date 2019/12/17 10:16
 * @Version 1.0
 */
@Controller
public class HelloController {
//    @Autowired
//    private StudentProperties studentProperties;
//
//    @RequestMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        return studentProperties.getName()+studentProperties.getAge()+"岁！";
//    }

//    @RequestMapping("/")
//    public String hello(Model m) {
//        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
//        return "hello";
//    }

    @RequestMapping("/")
    public String hello() {
        return "jsp/hello";
    }
}
