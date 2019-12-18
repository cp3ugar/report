package indi.xk.report.controller;

import indi.xk.report.pojo.Student;
import indi.xk.report.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/17 11:53
 * @Version 1.0
 */
@Controller
public class StudentController{
    @Autowired
    private StudentService studentService;

    @RequestMapping("/listStudent")
    public String listStudent(Model model){
        List<Student> students = studentService.findAll();
        model.addAttribute("students",students);
        return "listStudent";
    }
}
