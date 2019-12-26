package indi.xk.report.service;

import indi.xk.report.pojo.Student;
import indi.xk.report.pojo.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/18 15:12
 * @Version 1.0
 */
@Component
public interface StudentService {
    /**
     * list
     * @return
     */
    List<StudentDTO> findAll();

    /**
     * 添加
     * @param student
     */
    void addStudent(StudentDTO student);
    void add();
    void addbg();
    void addtz();
}
