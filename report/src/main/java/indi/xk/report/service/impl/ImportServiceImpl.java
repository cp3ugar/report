package indi.xk.report.service.impl;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import indi.xk.report.mapper.StudentMapper;
import indi.xk.report.pojo.dto.StudentDTO;
import indi.xk.report.service.ImportService;
import indi.xk.report.utils.Utils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/23 16:45
 * @Version 1.0
 */
@Service
public class ImportServiceImpl implements ImportService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 导入excel
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importExcel(InputStream is) throws IOException,InvalidFormatException {
        List<StudentDTO> students = new ArrayList<>();
        HSSFWorkbook book = new HSSFWorkbook(is);
        HSSFSheet sheet = book.getSheetAt(0);

        /**
         * 通常第一行都是标题，所以从第二行开始读取数据
         */
        for(int i=1; i<sheet.getLastRowNum()+1; i++) {
            HSSFRow row = sheet.getRow(i);
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            //名称
            Integer studentId = Integer.valueOf(row.getCell(0).getStringCellValue());
            String name = row.getCell(1).getStringCellValue();
            String sex = row.getCell(2).getStringCellValue();
            Integer age = Integer.valueOf(row.getCell(3).getStringCellValue());
            String birthday = row.getCell(4).getStringCellValue();
            StudentDTO student = new StudentDTO();
            student.setStudentId(studentId);
            student.setName(name);
            student.setSex(sex);
            student.setAge(age);
            student.setBirthday(birthday);
            students.add(student);
        }
        for (StudentDTO student : students) {
            System.err.println(student);
        }
        //批量保存
        if(Utils.isNotEmpty(students)){
            studentMapper.batchInsert(students);
        }
    }
}
