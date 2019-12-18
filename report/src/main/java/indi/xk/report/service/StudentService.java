package indi.xk.report.service;

import indi.xk.report.pojo.Student;
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
    List<Student> findAll();
}
