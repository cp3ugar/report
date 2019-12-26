package indi.xk.report.controller;

import indi.xk.report.pojo.Student;
import indi.xk.report.pojo.dto.StudentDTO;
import indi.xk.report.service.StudentService;
import indi.xk.report.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/listStudent")
    public String listStudent(Model model){
        List<StudentDTO> students = studentService.findAll();
        model.addAttribute("students",students);
        return "listStudent";
    }

    @PostMapping("/addStudent")
    @ResponseBody
    public ReturnObject addStudent(@Validated StudentDTO student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            for(ObjectError error : bindingResult.getAllErrors()){
                return ReturnObject.outError(error.getDefaultMessage());
            }
        }
        studentService.addStudent(student);
        return ReturnObject.outSuccess("添加成功！");
    }
    @GetMapping ("/add")
    @ResponseBody
    public void add(){

        studentService.add();

    }
    @GetMapping ("/addbg")
    @ResponseBody
    public void addbg(){

        studentService.addbg();

    }
    @GetMapping ("/addtz")
    @ResponseBody
    public void addtz(){

        studentService.addtz();

    }
}
