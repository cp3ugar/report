package indi.xk.report.utils;

import indi.xk.report.pojo.dto.AbstractImportDTO;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.List;

public abstract class BaseImportExcel {
    private static final Logger logger = LoggerFactory.getLogger(BaseImportExcel.class);

    @Value("${local.path}")
    private String localPath;


    /**
     * 输出错误文件
     * @param sourceFile 导入文件
     * @param data
     * @return 错误文件路径
     */
    public <T extends AbstractImportDTO> BatchImportResultDTO generateErrorFile(File sourceFile, List<T> data) {
        BatchImportResultDTO resultDTO = new BatchImportResultDTO();
        int failure = (int) data.stream().filter(AbstractImportDTO::isError).count();
        int success = data.size() - failure;
        int total = data.size();
        resultDTO.setTotal(total);
        resultDTO.setSuccess(success);
        resultDTO.setFailure(failure);
        if (failure == 0) {
            return resultDTO;
        }
        InputStream is = null;
        try {
            is = new FileInputStream(sourceFile);
            HSSFWorkbook book = new HSSFWorkbook(is);
            HSSFSheet sheet = book.getSheetAt(0);
            Row row = sheet.getRow(0);
            //获取总行数
            int rowNum = sheet.getLastRowNum();
            // 获取总列数
            int columnNum = row.getLastCellNum();
            //新建单元格
            Cell myCell = row.createCell(columnNum);
            myCell.setCellValue("错误原因");
            // 循环行Row,删除正确的行，从后往前删除
            for (int i = data.size() - 1; i >= 0; i--) {
                AbstractImportDTO abstractImportDTO = data.get(i);
                Row dataRow = sheet.getRow(i+1);
                if (abstractImportDTO.isError()) {
                    //错误数据
                    Cell cell1 = dataRow.createCell(columnNum);
                    HSSFRichTextString errorStr = new HSSFRichTextString(abstractImportDTO.getErrorInfo());
                    HSSFFont font = book.createFont();
                    //定义Excel数据颜色，这里设置为蓝色
                    font.setColor(HSSFColor.RED.index);
                    errorStr.applyFont(font);
                    cell1.setCellValue(errorStr);
                } else {
                    sheet.removeRow(dataRow);
                    sheet.shiftRows(i+2,rowNum,-1);
                }
            }
            //新建保存错误文件的目录
            String errorPath = localPath + "ErrorFile\\";
            File f = new File(errorPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            //保存到本地
            String errorFile = errorPath + sourceFile.getName();
            FileOutputStream outputStream = new FileOutputStream(errorFile);
            //将Excel写入输出流中
            book.write(outputStream);
            outputStream.flush();
            outputStream.close();
            resultDTO.setErrorFileAddress(errorFile);
            return resultDTO;
        } catch (Exception e) {
            logger.error("e", e);
            throw new BaseRuntimeException(500, "生成错误文件异常" + e.getMessage());
        } finally {
            try {
                is.close();
                if (sourceFile.exists()) {
                    sourceFile.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
