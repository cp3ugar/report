package indi.xk.report.service.impl;

import indi.xk.report.mapper.StudentMapper;
import indi.xk.report.pojo.Ssbgxx;
import indi.xk.report.pojo.Ssbqxx;
import indi.xk.report.pojo.Ssxx;
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
    public void importExcel(InputStream is) throws IOException{
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

    /**
     * 导入excel
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importExcelToThreeTable(InputStream is) throws IOException{
        List<Ssbqxx> ssbqxxs = new ArrayList<>();
        List<Ssbgxx> ssbgxxs = new ArrayList<>();
        List<Ssxx> ssxxs = new ArrayList<>();
        HSSFWorkbook book = new HSSFWorkbook(is);
        HSSFSheet ssbqxxsSheet = book.getSheetAt(0);
        HSSFSheet ssbgxxsSheet = book.getSheetAt(1);
        HSSFSheet ssxxsSheet = book.getSheetAt(2);

        //查封台账
        for(int i=1; i<ssbqxxsSheet.getLastRowNum()+1; i++) {
            HSSFRow row = ssbqxxsSheet.getRow(i);
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
            row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
            //名称
            Integer studentId = Integer.valueOf(row.getCell(0).getStringCellValue());
            String name = row.getCell(1).getStringCellValue();
            String sex = row.getCell(2).getStringCellValue();
            Integer age = Integer.valueOf(row.getCell(3).getStringCellValue());
            String birthday = row.getCell(4).getStringCellValue();
            Ssbqxx ssbqxx = new Ssbqxx();
            ssbqxxs.add(ssbqxx);
        }
        for (Ssbqxx ssbqxx : ssbqxxs) {
            System.err.println(ssbqxx);
        }

        //被告人信息维护
        for(int i=1; i<ssbqxxsSheet.getLastRowNum()+1; i++) {
            HSSFRow row = ssbqxxsSheet.getRow(i);
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            //名称
            Integer studentId = Integer.valueOf(row.getCell(0).getStringCellValue());
            String name = row.getCell(1).getStringCellValue();
            String sex = row.getCell(2).getStringCellValue();
            Integer age = Integer.valueOf(row.getCell(3).getStringCellValue());
            String birthday = row.getCell(4).getStringCellValue();
            Ssbgxx ssbgxx = new Ssbgxx();
            ssbgxxs.add(ssbgxx);
        }
        for (Ssbgxx ssbgxx : ssbgxxs) {
            System.err.println(ssbgxx);
        }

        //诉讼台账
        for(int i=1; i<ssxxsSheet.getLastRowNum()+1; i++) {
            HSSFRow row = ssxxsSheet.getRow(i);
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
            //名称
            Integer studentId = Integer.valueOf(row.getCell(0).getStringCellValue());
            String name = row.getCell(1).getStringCellValue();
            String sex = row.getCell(2).getStringCellValue();
            Integer age = Integer.valueOf(row.getCell(3).getStringCellValue());
            String birthday = row.getCell(4).getStringCellValue();
            Ssxx ssxx = new Ssxx();
            ssxxs.add(ssxx);
        }
        for (Ssxx ssxx : ssxxs) {
            System.err.println(ssxx);
        }
        //批量保存
        if(Utils.isNotEmpty(ssbgxxs)){
            studentMapper.batchInsertSsbgxx(ssbgxxs);
        }
        if(Utils.isNotEmpty(ssbqxxs)){
            studentMapper.batchInsertSsbqxx(ssbqxxs);
        }
        if(Utils.isNotEmpty(ssxxs)){
            studentMapper.batchInsertSsxx(ssxxs);
        }
    }
}
