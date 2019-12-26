package indi.xk.report.mapper;

import indi.xk.report.pojo.dto.StudentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/17 12:20
 * @Version 1.0
 */
@Mapper
//@Component
public interface StudentMapper {
    /**
     * list
     * @return
     */
    @Select("select * from student")
    List<StudentDTO> findAll();

    /**
     * 批量保存
     * @param students
     */
    void batchInsert(@Param("list") List<StudentDTO> students);

    /**
     * 添加
     * @param student
     * @return
     */
    @Insert("insert into student (student_id,`name`,sex,age,birthday) " +
            "values (#{studentId},#{name},#{sex},#{age},#{birthday})")
    int addStudent(StudentDTO student);
}
