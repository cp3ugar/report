package indi.xk.report.service.impl;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import indi.xk.report.mapper.StudentMapper;
import indi.xk.report.pojo.Ssbqxx;
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
import java.util.UUID;

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

    @Override
    public void importExceltk(InputStream is) throws IOException {
        List<Ssbqxx> Ssbqxx = new ArrayList<>();
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
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);

            //名称
            String bqid= UUID.randomUUID().toString();
            String ssid=row.getCell(0).getStringCellValue();
            String bqjd = row.getCell(1).getStringCellValue();
            String bqlx =  row.getCell(2).getStringCellValue();
            String bqfs =row.getCell(3).getStringCellValue();
            String sflhcf = row.getCell(4).getStringCellValue();
            String cqzt = row.getCell(5).getStringCellValue();
            String bqqsr=row.getCell(6).getStringCellValue();
            String bqccjz=row.getCell(7).getStringCellValue();
            String czzt	=row.getCell(8).getStringCellValue();
            String sfxf=row.getCell(9).getStringCellValue();
            String cfxfr=row.getCell(10).getStringCellValue();
            String jbfg=row.getCell(11).getStringCellValue();
            Ssbqxx ss =new Ssbqxx();



            ss.setBqid(bqid);
            ss.setSsid(ssid);
            ss.setBqjd(bqjd);
            ss.setBqlx(bqlx);
            ss.setBqfs(bqfs);
            ss.setSflhcf(sflhcf);
            ss.setCqzt(cqzt);
            ss.setBqqsr(bqqsr);
            ss.setBqccjz(bqccjz);
            ss.setCzzt(czzt);
            ss.setSfxf(sfxf);
            ss.setCfxfr(cfxfr);
            ss.setJbfg(jbfg);
            Ssbqxx.add(ss);
        }

        //批量保存
        if(Utils.isNotEmpty(Ssbqxx)){
            studentMapper.batchInsert1(Ssbqxx);
        }
    }

}
