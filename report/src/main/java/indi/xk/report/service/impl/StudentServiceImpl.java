package indi.xk.report.service.impl;

import indi.xk.report.mapper.StudentMapper;
import indi.xk.report.pojo.Ssbgxx;
import indi.xk.report.pojo.Ssbqxx;
import indi.xk.report.pojo.Ssxx;
import indi.xk.report.pojo.Student;
import indi.xk.report.pojo.dto.StudentDTO;
import indi.xk.report.service.StudentService;
import indi.xk.report.utils.BaseRuntimeException;
import indi.xk.report.utils.PageView;
import indi.xk.report.utils.PagingUtil;
import indi.xk.report.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public PageView<StudentDTO> findAll(PageView pageView) {
        Map map = new HashMap(1);
        Integer count = studentMapper.count();
        PagingUtil.pagingUtil(count, pageView);
        map.put("pageView", pageView);
        List<StudentDTO> list = studentMapper.findAll(map);
        if (Utils.isEmpty(pageView)) {
            pageView = new PageView();
        }
        pageView.setRecords(list);
        return pageView;
    }

    /**
     * 添加
     * @param
     * @return
     * @author xk
     * @date 2019/12/23  17:57
     */
    @Override
    public void addStudent(StudentDTO student) {
        int row = studentMapper.addStudent(student);
        if(row != 1){
            throw new BaseRuntimeException(500,"添加失败！");
        }
    }
}
