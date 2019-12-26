package indi.xk.report.controller;

import indi.xk.report.pojo.Student;
import indi.xk.report.pojo.dto.StudentDTO;
import indi.xk.report.service.StudentService;
import indi.xk.report.utils.PageView;
import indi.xk.report.utils.ReturnObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xk
 * @Date 2019/12/17 11:53
 * @Version 1.0
 */
@Controller
public class StudentController{
    @Autowired
    private StudentService studentService;

    @GetMapping("/toListStudent")
    public String listStudent(){
        return "listStudent";
    }

    @GetMapping("/listStudent")
    @ResponseBody
    public ReturnObject listStudent(@RequestParam("page")Integer pageNow,@RequestParam("limit")Integer pageSize){
        PageView pageView = new PageView(pageSize,pageNow);
        PageView<StudentDTO> students = studentService.findAll(pageView);
        return new ReturnObject(0,"",students.getRowCount(),students.getRecords());
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
}
