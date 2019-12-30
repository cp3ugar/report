package indi.xk.report.controller;

import indi.xk.report.pojo.dto.StudentDTO;
import indi.xk.report.service.StudentService;
import indi.xk.report.utils.PageView;
import indi.xk.report.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author xk
 * @Date 2019/12/17 11:53
 * @Version 1.0
 */
@Controller
public class StudentController extends BaseController{
    @Autowired
    private StudentService studentService;

    @GetMapping("/toListStudent")
    public String listStudent(){
        return "listStudent";
    }

    @GetMapping("/listStudent")
    @ResponseBody
    public ReturnObject listStudent(@RequestParam(value = "page",defaultValue = "1")Integer pageNow,
                                    @RequestParam(value = "limit",defaultValue = "10")Integer pageSize){
        PageView pageView = new PageView(pageSize,pageNow);
        PageView<StudentDTO> students = studentService.findAll(pageView);
        return new ReturnObject(0,"",students.getRowCount(),students.getRecords());
    }

    @PostMapping("/addStudent")
    @ResponseBody
    public ReturnObject addStudent(@Validated StudentDTO student){
        studentService.addStudent(student);
        return ReturnObject.outSuccess("添加成功！");
    }

    @GetMapping("/deleteStudent")
    @ResponseBody
    public ReturnObject deleteStudent(Integer id){
        if(id == null){
            return ReturnObject.outError("缺少学生id！");
        }
        studentService.deleteStudent(id);
        return ReturnObject.outSuccess("删除成功！");
    }
}
