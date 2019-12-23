package indi.xk.report.mapper;

import indi.xk.report.pojo.Student;
import indi.xk.report.pojo.dto.StudentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/17 12:20
 * @Version 1.0
 */
@Mapper
public interface StudentMapper {
    /**
     * list
     * @return
     */
    @Select("select * from student")
    List<StudentDTO> findAll();
}
