package indi.xk.report.mapper;

import indi.xk.report.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/17 12:20
 * @Version 1.0
 */
@Repository
public interface StudentMapper {
    List<Student> findAll();
}
