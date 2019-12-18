package indi.xk.report.controller;

import indi.xk.report.mapper.StudentMapper;
import indi.xk.report.pojo.Student;
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
    private StudentMapper studentMapper;

    @RequestMapping("/listStudent")
    public String listStudent(Model model){
        List<Student> students = studentMapper.findAll();
        model.addAttribute("students",students);
        return "listStudent";
    }
}
