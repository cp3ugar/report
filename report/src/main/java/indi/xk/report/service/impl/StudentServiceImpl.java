package indi.xk.report.service.impl;

import indi.xk.report.mapper.StudentMapper;
import indi.xk.report.pojo.Student;
import indi.xk.report.pojo.dto.StudentDTO;
import indi.xk.report.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/18 15:12
 * @Version 1.0
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * list
     * @param
     * @return
     * @author xk
     * @date 2019/12/18  15:13
     */
    @Override
    public List<StudentDTO> findAll() {
        return studentMapper.findAll();
    }
}
