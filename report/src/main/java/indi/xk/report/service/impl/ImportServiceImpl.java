package indi.xk.report.service.impl;

import indi.xk.report.mapper.ImportMapper;
import indi.xk.report.mapper.StudentMapper;
import indi.xk.report.pojo.*;
import indi.xk.report.pojo.dto.StudentDTO;
import indi.xk.report.pojo.dto.StudentImportDTO;
import indi.xk.report.service.ImportService;
import indi.xk.report.utils.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author xk
 */
@Service
public class ImportServiceImpl extends BaseImportExcel implements ImportService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ImportMapper importMapper;

    @Value("${local.path}")
    private String localPath;



    /**
     * 导入学生
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BatchImportResultDTO importStudent(MultipartFile file) throws IOException {
        HSSFWorkbook book;
        InputStream inputStream;
        InputStream is;
        File targetFile;
        try {
            inputStream = file.getInputStream();
            InputStream is1 = file.getInputStream();
            book = new HSSFWorkbook(inputStream);
            byte[] bytes = IOUtils.toByteArray(is1);
            is = new ByteArrayInputStream(bytes);
            String tempPath = localPath + Utils.getUUid("student_") + file.getOriginalFilename();
            targetFile = Utils.getCopyFile(localPath, tempPath, is);
        } catch (Exception e) {
            throw new BaseRuntimeException(2, "文件解析异常");
        }

        List<StudentImportDTO> allData = new ArrayList<>();
        List<StudentImportDTO> correctData = new ArrayList<>();
        List<StudentImportDTO> errorData = new ArrayList<>();
        HSSFSheet sheet = book.getSheetAt(0);

        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            HSSFRow row = sheet.getRow(i);
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            Integer studentId = Integer.valueOf(row.getCell(0).getStringCellValue());
            String name;
            if(Utils.isEmpty(row.getCell(1))){
                name = "";
            }else{
                name = row.getCell(1).getStringCellValue();
            }
            String sex;
            if(Utils.isEmpty(row.getCell(2))){
                sex = "";
            }else{
                sex = row.getCell(2).getStringCellValue();
            }
            Integer age;
            if(Utils.isEmpty(row.getCell(3))){
                age = 0;
            }else{
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                age = Integer.valueOf(row.getCell(3).getStringCellValue());
            }
            String birthday;
            if(Utils.isEmpty(row.getCell(4))){
                birthday = Utils.getNowTime("yyyy/MM/dd");
            }else{
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                birthday = row.getCell(4).getStringCellValue();
            }

            StudentImportDTO student = new StudentImportDTO();
            student.setStudentId(studentId);
            student.setName(name);
            student.setSex(sex);
            student.setAge(age);
            student.setBirthday(birthday);

            //处理数据
            int res = studentMapper.queryStudentByStudentId(studentId);
            if(res > 0){
                student.setError(true);
                student.setErrorInfo(student.getErrorInfo()+"学号重复;");
                errorData.add(student);
            }
            validateStudentId(student);
            validateNotNull(student,student.getName(),"姓名");
            convertSex(student);
            validateAge(student);
            validateBirthday(student);
            allData.add(student);
        }
        for (StudentImportDTO student : allData) {
            if (student.isError()) {
                errorData.add(student);
            } else {
                correctData.add(student);
            }
        }
        for(StudentImportDTO studentDTO : errorData){
            System.out.println(studentDTO);
        }
        //批量保存
        if (Utils.isNotEmpty(correctData)) {
            studentMapper.importStudent(correctData);
        }
        return generateErrorFile(targetFile, allData);
    }

    /**
     * 验证生日
     * @param student
     */
    private void validateBirthday(StudentImportDTO student) {
        String birthday = student.getBirthday();
        validateNotNull(student,birthday,"生日");
        boolean isBirthday = Utils.validDate(birthday,"yyyy-MM-dd");
        if(!isBirthday){
            student.setError(true);
            student.setErrorInfo(student.getErrorInfo() + "生日格式错误;");
        }
        String now = Utils.getNowTime("yyyy-MM-dd");
        if(birthday.compareTo(now) >= 0){
            student.setError(true);
            student.setErrorInfo(student.getErrorInfo() + "生日日期错误;");
        }
    }

    /**
     * 验证年龄
     * @param student
     */
    private void validateAge(StudentImportDTO student) {
        Integer age = student.getAge();
        validateNotNull(student,age,"年龄");
        if(age < 7 || age > 30){
            student.setError(true);
            student.setErrorInfo(student.getErrorInfo() + "年龄错误;");
        }
    }

    /**
     * 性别转换
     * @param student
     */
    private void convertSex(StudentImportDTO student) {
        String sex = student.getSex();
        validateNotNull(student,sex,"性别");
        if("男".equals(sex)){
            student.setSex("m");
        }else if("女".equals(sex)){
            student.setSex("f");
        }else{
            student.setError(true);
            student.setErrorInfo(student.getErrorInfo() + "性别错误;");
        }
    }

    /**
     * 验证非空
     * @param source
     */
    private void validateNotNull(StudentImportDTO source,Object getter,String describe) {
        if (Utils.isEmpty(getter)) {
            source.setError(true);
            source.setErrorInfo(source.getErrorInfo() + describe + "不能为空;");
        }
    }

    /**
     * 验证学号
     * @param student
     */
    private void validateStudentId(StudentImportDTO student) {
        Integer studentId = student.getStudentId();
        validateNotNull(student,studentId,"学号");
        if (Utils.isNotEmpty(student.getStudentId())) {
            if (studentId > 9999 || studentId < 1000) {
                student.setError(true);
                student.setErrorInfo(student.getErrorInfo() + "学号必须为4位;");
            }
        }
    }

    /**
     * 导入诉讼台账
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importLitigation(InputStream is) throws IOException {
        List<Ssbqxx> ssbqxxs = new ArrayList<>();
        List<Ssbgxx> ssbgxxs = new ArrayList<>();
        List<Ssxx> ssxxs = new ArrayList<>();
        List<Sszxxx> sszxxxs = new ArrayList<>();
        List<Sslaxx> sslaxxs = new ArrayList<>();
        HSSFWorkbook book = new HSSFWorkbook(is);
        HSSFSheet ssbqxxsSheet = book.getSheetAt(0);
        HSSFSheet ssbgxxsSheet = book.getSheetAt(1);
        HSSFSheet ssxxsSheet = book.getSheetAt(3);

        //查封台账
        for (int i = 1; i < ssbqxxsSheet.getLastRowNum() + 1; i++) {
            HSSFRow row = ssbqxxsSheet.getRow(i);
            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
            //名称
            String bqid = UUID.randomUUID().toString();
            String ssid = row.getCell(0).getStringCellValue();
            String bqjd = row.getCell(1).getStringCellValue();
            String bqlx = row.getCell(2).getStringCellValue();
            String bqfs = row.getCell(3).getStringCellValue();
            String sflhcf = row.getCell(4).getStringCellValue();
            String cqzt = row.getCell(5).getStringCellValue();
            if (row.getCell(6).getCellType() != Cell.CELL_TYPE_BLANK
                    && row.getCell(6).getCellType() != Cell.CELL_TYPE_NUMERIC) {
                throw new BaseRuntimeException(500, "日期格式不正确！");
            }
            Date bqqsrDate = row.getCell(6).getDateCellValue();
            String bqqsr = Utils.getStringDate(bqqsrDate,"yyyy/MM/dd");
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
            sflhcf = convertSflhcf(sflhcf);
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

        //被告人信息维护
        for (int i = 1; i < ssbgxxsSheet.getLastRowNum() + 1; i++) {
            HSSFRow row = ssbgxxsSheet.getRow(i);
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            //名称
            String bgid = UUID.randomUUID().toString();
            String ssid = row.getCell(0).getStringCellValue();
            String mc = row.getCell(1).getStringCellValue();
            String zjhm = row.getCell(2).getStringCellValue();
            String ybglx = row.getCell(3).getStringCellValue();
            ybglx = ybglxConvert(ybglx);
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

        //诉讼台账
        for (int i = 1; i < ssxxsSheet.getLastRowNum() + 1; i++) {
            HSSFRow row = ssxxsSheet.getRow(i);
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
            //名称
            String id = UUID.randomUUID().toString();
            String zxid = UUID.randomUUID().toString();
            String laid = UUID.randomUUID().toString();
            String jkid = row.getCell(0).getStringCellValue();
            String dfmc = row.getCell(2).getStringCellValue();
            String jkrzjh = row.getCell(3).getStringCellValue();
            if (row.getCell(4).getCellType() != Cell.CELL_TYPE_BLANK
                    && row.getCell(4).getCellType() != Cell.CELL_TYPE_NUMERIC) {
                throw new BaseRuntimeException(500, "日期格式不正确！");
            }
            Date larqDate = row.getCell(4).getDateCellValue();
            String larq = Utils.getStringDate(larqDate,"yyyy/MM/dd");
            if (row.getCell(6).getCellType() != Cell.CELL_TYPE_BLANK
                    && row.getCell(6).getCellType() != Cell.CELL_TYPE_NUMERIC) {
                throw new BaseRuntimeException(500, "日期格式不正确！");
            }
            Date fqrqDate = row.getCell(6).getDateCellValue();
            String fqrq = Utils.getStringDate(fqrqDate,"yyyy/MM/dd");
            String sljd = row.getCell(7).getStringCellValue();
            String ssft = row.getCell(8).getStringCellValue();
            String slfg = row.getCell(9).getStringCellValue();
            String bdeStr = row.getCell(10).getStringCellValue();
            BigDecimal bde = null;
            if (Utils.isNotEmpty(bdeStr)) {
                bde = new BigDecimal(bdeStr);
            }
            String sljg = row.getCell(11).getStringCellValue();
            if (row.getCell(12).getCellType() != Cell.CELL_TYPE_BLANK
                    && row.getCell(12).getCellType() != Cell.CELL_TYPE_NUMERIC) {
                throw new BaseRuntimeException(500, "日期格式不正确！");
            }
            if (Utils.isNotEmpty(row.getCell(10).getStringCellValue())) {
                bde = new BigDecimal(row.getCell(10).getStringCellValue());
            }
            Date pjrDate = row.getCell(12).getDateCellValue();
            String pjr = Utils.getStringDate(pjrDate,"yyyy/MM/dd");
            String pjsah = row.getCell(13).getStringCellValue();
            if (row.getCell(14).getCellType() != Cell.CELL_TYPE_BLANK
                    && row.getCell(14).getCellType() != Cell.CELL_TYPE_NUMERIC) {
                throw new BaseRuntimeException(500, "日期格式不正确！");
            }
            Date flwssxrDate = row.getCell(14).getDateCellValue();
            String flwssxr = Utils.getStringDate(flwssxrDate,"yyyy/MM/dd");
            String zxah = row.getCell(16).getStringCellValue();
            String zxfy = row.getCell(17).getStringCellValue();
            String zxfg = row.getCell(18).getStringCellValue();
            if (row.getCell(19).getCellType() != Cell.CELL_TYPE_BLANK
                    && row.getCell(19).getCellType() != Cell.CELL_TYPE_NUMERIC) {
                throw new BaseRuntimeException(500, "日期格式不正确！");
            }
            Date ssxmdrqDate = row.getCell(19).getDateCellValue();
            String ssxmdrq = Utils.getStringDate(ssxmdrqDate,"yyyy/MM/dd");
            String sfdczxhj = row.getCell(20).getStringCellValue();
            sfdczxhj = convertSfdczxhj(sfdczxhj);
            String orgCodeSs = row.getCell(21).getStringCellValue();
            orgCodeSs = orgCodeSsConvert(orgCodeSs);
            Ssxx ssxx = new Ssxx();
            ssxx.setId(id);
            ssxx.setJkid(jkid);
            ssxx.setDfmc(dfmc);
            ssxx.setJkrzjh(jkrzjh);
            ssxx.setFqrq(fqrq);
            ssxx.setSljd(sljd);
            ssxx.setSlfg(slfg);
            ssxx.setBde(bde);
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
            Sslaxx sslaxx = new Sslaxx();
            sslaxx.setId(laid);
            sslaxx.setSsid(jkid);
            sslaxx.setLarq(larq);
            sslaxx.setKtrq(fqrq);
            sslaxx.setSljd(sljd);
            sslaxx.setSsft(ssft);
            sslaxx.setZsfg(slfg);
            sslaxx.setBdje(bdeStr);
            sslaxx.setSljg(sljg);
            sslaxx.setSjrq(pjr);
            sslaxx.setFlwssxr(flwssxr);
            sslaxxs.add(sslaxx);
        }

        //批量保存
        if (Utils.isNotEmpty(ssbgxxs)) {
            importMapper.batchInsertSsbgxx(ssbgxxs);
        }
        if (Utils.isNotEmpty(ssbqxxs)) {
            importMapper.batchInsertSsbqxx(ssbqxxs);
        }
        if (Utils.isNotEmpty(ssxxs)) {
            importMapper.batchInsertSsxx(ssxxs);
        }
        if (Utils.isNotEmpty(sszxxxs)) {
            importMapper.batchInsertSszxxx(sszxxxs);
        }
        if (Utils.isNotEmpty(sslaxxs)) {
            importMapper.batchInsertSslaxxs(sslaxxs);
        }
    }

    /**
     * 诉讼管理机构转换
     *
     * @param
     * @return
     * @author xk
     * @date 2019/12/27  10:50
     */
    private String orgCodeSsConvert(String value) {
        //查询所有诉讼管理机构
        List<Org> orgs = importMapper.queryAllOrgs();
        for (Org org : orgs) {
            if (org.getOrgName().equals(value)) {
                return org.getOrgId();
            }
        }
        return null;
    }

    /**
     * 被告人类型转换
     *
     * @param
     * @return
     * @author xk
     * @date 2019/12/27  10:50
     */
    private String ybglxConvert(String value) {
        String[] sflhcfArr = {"错误类型", "借款人", "担保人", "其他", "共同借款人"};
        for (int i = 0; i < sflhcfArr.length; i++) {
            if (sflhcfArr[i].equals(value)) {
                return "" + i + "";
            }
        }
        return null;
    }

    /**
     * 是否达成和解转换
     * @param
     * @return
     * @author xk
     * @date 2019/12/27  10:50
     */
    private String convertSfdczxhj(String value) {
        String[] sflhcfArr = {"错误状态", "是", "否"};
        for (int i = 0; i < sflhcfArr.length; i++) {
            if (sflhcfArr[i].equals(value)) {
                return "" + i + "";
            }
        }
        return null;
    }

    /**
     * 查封阶段转换
     *
     * @param
     * @return
     * @author xk
     * @date 2019/12/27  10:50
     */
    private String convertBqjd(String value) {
        String[] bqlxArr = {"错误阶段", "诉前", "诉中", "执行"};
        for (int i = 0; i < bqlxArr.length; i++) {
            if (bqlxArr[i].equals(value)) {
                return "" + i + "";
            }
        }
        return null;
    }

    /**
     * 查封资产类型转换
     *
     * @param
     * @return
     * @author xk
     * @date 2019/12/27  10:50
     */
    private String convertBqlx(String value) {
        String[] bqlxArr = {"错误类型", "土地", "房产", "交通工具", "设备", "有价证券", "银行存款", "银行存款", "其他"};
        for (int i = 0; i < bqlxArr.length; i++) {
            if (bqlxArr[i].equals(value)) {
                return "" + i + "";
            }
        }
        return null;
    }

    /**
     * 查封方式转换
     *
     * @param
     * @return
     * @author xk
     * @date 2019/12/27  10:50
     */
    private String convertBqfs(String value) {
        String[] bqfsArr = {"错误方式", "查封", "冻结", "扣押", "其他"};
        for (int i = 0; i < bqfsArr.length; i++) {
            if (bqfsArr[i].equals(value)) {
                return "" + i + "";
            }
        }
        return null;
    }

    /**
     * 是否轮候查封转换
     *
     * @param
     * @return
     * @author xk
     * @date 2019/12/27  10:50
     */
    private String convertSflhcf(String value) {
        String[] sflhcfArr = {"错误状态", "是", "否"};
        for (int i = 0; i < sflhcfArr.length; i++) {
            if (sflhcfArr[i].equals(value)) {
                return "" + i + "";
            }
        }
        return null;
    }

    /**
     * 查封状态转换
     *
     * @param
     * @return
     * @author xk
     * @date 2019/12/27  10:51
     */
    private String convertCqzt(String value) {
        String[] cqztArr = {"错误状态", "一封", "二封", "三封", "四封", "四封以上"};
        for (int i = 0; i < cqztArr.length; i++) {
            if (cqztArr[i].equals(value)) {
                return "" + i + "";
            }
        }
        return null;
    }

    /**
     * 处置状态转换
     *
     * @param
     * @return
     * @author xk
     * @date 2019/12/27  10:51
     */
    private String convertCzzt(String value) {
        String[] czztArr = {"错误状态", "未处置", "处置中", "处置完毕", "解封"};
        for (int i = 0; i < czztArr.length; i++) {
            if (czztArr[i].equals(value)) {
                return "" + i + "";
            }
        }
        return null;
    }
}
