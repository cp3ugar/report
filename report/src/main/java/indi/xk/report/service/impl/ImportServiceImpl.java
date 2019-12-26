package indi.xk.report.service.impl;

import indi.xk.report.mapper.StudentMapper;
import indi.xk.report.pojo.Ssbgxx;
import indi.xk.report.pojo.Ssbqxx;
import indi.xk.report.pojo.Ssxx;
import indi.xk.report.pojo.Sszxxx;
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
import java.math.BigDecimal;
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
        List<Sszxxx> sszxxxs = new ArrayList<>();
        HSSFWorkbook book = new HSSFWorkbook(is);
        HSSFSheet ssbqxxsSheet = book.getSheetAt(0);
        HSSFSheet ssbgxxsSheet = book.getSheetAt(1);
        HSSFSheet ssxxsSheet = book.getSheetAt(3);

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
//            row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
            //名称
            String bqid = UUID.randomUUID().toString();
            String ssid = row.getCell(0).getStringCellValue();
            String bqjd = row.getCell(1).getStringCellValue();
            String bqlx = row.getCell(2).getStringCellValue();
            String bqfs = row.getCell(3).getStringCellValue();
            String sflhcf = row.getCell(4).getStringCellValue();
            String cqzt = row.getCell(5).getStringCellValue();
            String bqqsr = row.getCell(6).getStringCellValue();
            String bqccjz = row.getCell(7).getStringCellValue();
            String czzt = row.getCell(8).getStringCellValue();
            String sfxf = row.getCell(9).getStringCellValue();
            String cfxfr = row.getCell(10).getStringCellValue();
            String jbfg = row.getCell(11).getStringCellValue();
            bqjd = convertBqjd(bqjd);
            bqlx = convertBqlx(bqlx);
            bqfs = convertBqfs(bqfs);
            cqzt = convertCqzt(cqzt);
            czzt = convertCzzt(czzt);
            sflhcf =  convertSflhcf(sflhcf);
            Ssbqxx ssbqxx = new Ssbqxx();
            ssbqxx.setBqid(bqid);
            ssbqxx.setSsid(ssid);
            ssbqxx.setBqjd(bqjd);
            ssbqxx.setBqlx(bqlx);
            ssbqxx.setBqfs(bqfs);
            ssbqxx.setSflhcf(sflhcf);
            ssbqxx.setCqzt(cqzt);
            ssbqxx.setBqqsr(bqqsr);
            ssbqxx.setBqccjz(bqccjz);
            ssbqxx.setCzzt(czzt);
            ssbqxx.setSfxf(sfxf);
            ssbqxx.setCfxfr(cfxfr);
            ssbqxx.setJbfg(jbfg);
            ssbqxxs.add(ssbqxx);
        }
        for (Ssbqxx ssbqxx : ssbqxxs) {
            System.err.println(ssbqxx);
        }

        //被告人信息维护
        for(int i=1; i<ssbgxxsSheet.getLastRowNum()+1; i++) {
            HSSFRow row = ssbgxxsSheet.getRow(i);
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            //名称
            String bgid = UUID.randomUUID().toString();
            String ssid = row.getCell(0).getStringCellValue();
            String mc = row.getCell(1).getStringCellValue();
            String zjhm = row.getCell(2).getStringCellValue();
            String ybglx = row.getCell(3).getStringCellValue();
            String lxdh = row.getCell(4).getStringCellValue();
            String xdz = row.getCell(5).getStringCellValue();
            Ssbgxx ssbgxx = new Ssbgxx();
            ssbgxx.setBgid(bgid);
            ssbgxx.setSsid(ssid);
            ssbgxx.setMc(mc);
            ssbgxx.setZjhm(zjhm);
            ssbgxx.setYbglx(ybglx);
            ssbgxx.setLxdh(lxdh);
            ssbgxx.setXdz(xdz);
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
            row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
            //名称
            String id = UUID.randomUUID().toString();
            String zxid = UUID.randomUUID().toString();
            String jkid = row.getCell(0).getStringCellValue();
            String dfmc = row.getCell(2).getStringCellValue();
            String jkrzjh = row.getCell(3).getStringCellValue();
            String fqrq = row.getCell(6).getStringCellValue();
            String sljd = row.getCell(7).getStringCellValue();
            String slfg = row.getCell(9).getStringCellValue();
            BigDecimal bde = null;
            if(Utils.isNotEmpty(row.getCell(10).getStringCellValue())){
                bde = new BigDecimal(row.getCell(10).getStringCellValue());
            }
            String jasm = row.getCell(11).getStringCellValue();
            String pjr = row.getCell(12).getStringCellValue();
            String pjsah = row.getCell(13).getStringCellValue();
            String zxah = row.getCell(16).getStringCellValue();
            String zxfy = row.getCell(17).getStringCellValue();
            String zxfg = row.getCell(18).getStringCellValue();
            String ssxmdrq = row.getCell(19).getStringCellValue();
            String sfdczxhj = row.getCell(20).getStringCellValue();
            sfdczxhj = convertSfdczxhj(sfdczxhj);
            String orgCodeSs = row.getCell(21).getStringCellValue();
            Ssxx ssxx = new Ssxx();
            ssxx.setId(id);
            ssxx.setJkid(jkid);
            ssxx.setDfmc(dfmc);
            ssxx.setJkrzjh(jkrzjh);
            ssxx.setFqrq(fqrq);
            ssxx.setSljd(sljd);
            ssxx.setSlfg(slfg);
            ssxx.setBde(bde);
            ssxx.setJasm(jasm);
            ssxx.setPjr(pjr);
            ssxx.setPjsah(pjsah);
            ssxx.setOrgCodeSs(orgCodeSs);
            ssxxs.add(ssxx);
            Sszxxx sszxxx = new Sszxxx();
            sszxxx.setZxid(zxid);
            sszxxx.setSsid(jkid);
            sszxxx.setZxah(zxah);
            sszxxx.setZxfy(zxfy);
            sszxxx.setZxfg(zxfg);
            sszxxx.setSsxmdrq(ssxmdrq);
            sszxxx.setSfdczxhj(sfdczxhj);
            sszxxxs.add(sszxxx);
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
        if(Utils.isNotEmpty(sszxxxs)){
            studentMapper.batchInsertSszxxx(sszxxxs);
        }
    }

    //是否达成和解转换
    private String convertSfdczxhj(String value) {
        String[] sflhcfArr = {"错误状态","是","否"};
        for(int i=0;i<sflhcfArr.length;i++){
            if(sflhcfArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }

    //查封阶段
    private String convertBqjd(String value) {
        String[] bqlxArr = {"错误阶段","诉前","诉中","执行"};
        for(int i=0;i<bqlxArr.length;i++){
            if(bqlxArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }

    //查封资产类型转换
    private String convertBqlx(String value){
        String[] bqlxArr = {"错误类型","土地","房产","交通工具","设备","有价证券","银行存款","银行存款","其他"};
        for(int i=0;i<bqlxArr.length;i++){
            if(bqlxArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }

    //查封方式转换
    private String convertBqfs(String value){
        String[] bqfsArr = {"错误方式","查封","冻结","扣押","其他"};
        for(int i=0;i<bqfsArr.length;i++){
            if(bqfsArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }

    //是否轮候查封转换
    private String convertSflhcf(String value){
        String[] sflhcfArr = {"错误状态","是","否"};
        for(int i=0;i<sflhcfArr.length;i++){
            if(sflhcfArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }

    //查封状态转换
    private String convertCqzt(String value){
        String[] cqztArr = {"错误状态","一封","二封","三封","四封","四封以上"};
        for(int i=0;i<cqztArr.length;i++){
            if(cqztArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }

    //处置状态转换
    private String convertCzzt(String value){
        String[] czztArr = {"错误状态","未处置","处置中","处置完毕","解封"};
        for(int i=0;i<czztArr.length;i++){
            if(czztArr[i].equals(value)){
                return ""+i+"";
            }
        }
        return null;
    }
}
