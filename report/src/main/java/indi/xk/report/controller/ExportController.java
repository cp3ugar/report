package indi.xk.report.controller;

import indi.xk.report.pojo.dto.StudentDTO;
import indi.xk.report.service.StudentService;
import indi.xk.report.utils.BaseRuntimeException;
import indi.xk.report.utils.Utils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/23 14:34
 * @Version 1.0
 */
@Controller
public class ExportController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
            String[] headers = {"学号", "姓名","性别","年龄","出生年月"};
            List<StudentDTO> datasList= studentService.findAll();
            if(Utils.isEmpty(datasList)){
                throw new BaseRuntimeException(500,"没有数据可导出！");
            }
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            //设置列宽
            sheet.setDefaultColumnWidth((short) 18);
            //创建第一行的对象，第一行一般用于填充标题内容。从第二行开始一般是数据
            HSSFRow row = sheet.createRow(0);
            for (short i = 0; i < headers.length; i++) {
                //创建单元格，每行多少数据就创建多少个单元格
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                //给单元格设置内容
                cell.setCellValue(text);
            }
            //遍历集合，将每个集合元素对象的每个值填充到单元格中
            for(int i=0;i<datasList.size();i++){
                StudentDTO student = datasList.get(i);
                //从第二行开始填充数据
                row = sheet.createRow(i+1);
                List<String> datas = new ArrayList<>();
                String studentId = student.getStudentId().toString();
                String name = student.getName();
                String sex = student.getSex();
                String age = student.getAge().toString();
                String birthday = student.getBirthday();
                datas.add(studentId);
                datas.add(name);
                datas.add(sex);
                datas.add(age);
                datas.add(birthday);
                for (int j=0;j<datas.size();j++) {
                    String obj = datas.get(j);
                    HSSFCell cell = row.createCell(j);
                    HSSFRichTextString richString = new HSSFRichTextString(obj);
                    HSSFFont font3 = workbook.createFont();
                    //定义Excel数据颜色，这里设置为蓝色
                    font3.setColor(HSSFColor.BLUE.index);
                    richString.applyFont(font3);
                    cell.setCellValue(richString);
                }
            }
            response.setContentType("application/octet-stream");
            //Excel文件名
            response.setHeader("Content-disposition", "attachment;filename="+"StudentInfo.xls");
            try {
                response.flushBuffer();
            } catch (IOException e) {
               throw new BaseRuntimeException(500,"导出错误！");
            }
            //将workbook中的内容写入输出流中
            workbook.write(response.getOutputStream());
    }
}
