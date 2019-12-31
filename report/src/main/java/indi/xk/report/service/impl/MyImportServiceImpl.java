package indi.xk.report.service.impl;

import indi.xk.report.mapper.ImportMapper;
import indi.xk.report.pojo.Ssbgxx;
import indi.xk.report.pojo.Ssbqxx;
import indi.xk.report.service.ImportService;
import indi.xk.report.service.MyImportService;
import indi.xk.report.utils.BaseRuntimeException;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by zxy on 2019/12/30.
 */
@Service
public class MyImportServiceImpl implements MyImportService {
    @Autowired
    private ImportMapper importMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importExceltoMysql(InputStream is) throws IOException {
        List<Ssbqxx> ssbqxx=new ArrayList<>();
        HSSFWorkbook book=new HSSFWorkbook(is);
        HSSFSheet ssbqxxSheet=book.getSheetAt(0);
        //查封台账
        for (int i = 1; i <ssbqxxSheet.getLastRowNum()+1 ; i++) {
            HSSFRow row =ssbqxxSheet.getRow(i);
            String bqid= UUID.randomUUID().toString();
            String ssid=row.getCell(0).getStringCellValue();
            String bqjd=row.getCell(1).getStringCellValue();
            String  bqlx=row.getCell(2).getStringCellValue();
            String bqfs=row.getCell(3).getStringCellValue();
            String sflhcf=row.getCell(4).getStringCellValue();
            String cqzt=row.getCell(5).getStringCellValue();
            if (row.getCell(6).getCellType()!= Cell.CELL_TYPE_BLANK&&row.getCell(6).getCellType()!=Cell.CELL_TYPE_NUMERIC){
                throw new BaseRuntimeException(500,"日期格式错误！");
            }
            Date bqqsrDate =row.getCell(6).getDateCellValue();
            String bqqsr= Utils.getStringDate(bqqsrDate,"yyyy/MM/dd");
            String bqccjz=row.getCell(7).getStringCellValue();
            String czzt=row.getCell(8).getStringCellValue();
            String sfxf=row.getCell(9).getStringCellValue();
            String cfxfr=row.getCell(10).getStringCellValue();
            String jbfg=row.getCell(11).getStringCellValue();
            bqjd=converTbqjd(bqjd);
            bqlx =converBqlx(bqlx);

            bqfs=converBqfs(bqfs);
            cqzt=converCqzt(cqzt);
            czzt=converCzzt(czzt);
            sflhcf=converSflhcf(sflhcf);
            Ssbqxx sb=new Ssbqxx();
            sb.setBqid(bqid);
            sb.setSsid(ssid);
            sb.setBqjd(bqjd);
            sb.setBqlx(bqlx);
            sb.setBqfs(bqfs);
            sb.setSflhcf(sflhcf);
            sb.setCqzt(cqzt);
            sb.setBqqsr(bqqsr);
            sb.setBqccjz(bqccjz);
            sb.setCzzt(czzt);
            sb.setSfxf(sfxf);
            sb.setCfxfr(cfxfr);
            sb.setJbfg(jbfg);
            ssbqxx.add(sb);
        }
        if (Utils.isNotEmpty(ssbqxx)) {
            importMapper.batchInsertSsbqxx(ssbqxx);
        }
    }
    public String converTbqjd(String value){
        String[] bqjdArr={"错误阶段","诉前","诉中","执行"};
        for (int i = 0; i <bqjdArr.length ; i++) {
            if (bqjdArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }
    public String converBqlx(String value){
        String[] bqlxArr={"错误类型", "土地", "房产", "交通工具", "设备", "有价证券", "银行存款", "银行存款", "其他"};
        for (int i = 0; i <bqlxArr.length ; i++) {
            if (bqlxArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }
    public String converBqfs(String value){
        String[] bqfsArr={"错误方式", "查封", "冻结", "扣押", "其他"};
        for (int i = 0; i <bqfsArr.length ; i++) {
            if (bqfsArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }
    public String converCqzt(String value){
        String[] cqztsArr={"错误状态", "一封", "二封", "三封", "四封", "四封以上"};
        for (int i = 0; i <cqztsArr.length ; i++) {
            if (cqztsArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }
    public String converCzzt(String value){
        String[] czztsArr={"错误状态", "未处置", "处置中", "处置完毕", "解封"};
        for (int i = 0; i <czztsArr.length ; i++) {
            if (czztsArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }
    public String converSflhcf(String value){
        String[] SflhcffArr={"错误状态", "未处置", "处置中", "处置完毕", "解封"};
        for (int i = 0; i <SflhcffArr.length ; i++) {
            if (SflhcffArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }
}
