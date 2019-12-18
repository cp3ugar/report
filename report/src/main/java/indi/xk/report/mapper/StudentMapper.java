package indi.xk.report.mapper;

import indi.xk.report.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/17 12:20
 * @Version 1.0
 */
@Mapper
@Repository
public interface StudentMapper {
    /**
     * list
     * @return
     */
    @Select("select * from student")
    List<Student> findAll();
}
