package indi.xk.report.service;

import indi.xk.report.pojo.dto.StudentDTO;
import indi.xk.report.utils.PageView;
import org.springframework.stereotype.Component;

/**
 * @Author xk
 * @Date 2019/12/18 15:12
 * @Version 1.0
 */
@Component
public interface StudentService {
    /**
     * 列表
     * @return
     */
    PageView<StudentDTO> findAll(PageView pageView);

    /**
     * 添加
     * @param student
     */
    void addStudent(StudentDTO student);

    /**
     * 删除
     * @param id
     */
    void deleteStudent(Integer id);
}
