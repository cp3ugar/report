package indi.xk.report.service.impl;

import com.aspose.words.*;
import indi.xk.report.mapper.TransformMapper;
import indi.xk.report.pojo.CommonWordDataSource;
import indi.xk.report.pojo.TransformEntity;
import indi.xk.report.service.TransformService;
import indi.xk.report.utils.FileUtil;
import indi.xk.report.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.*;


/**
 * @Author xk
 * @Date 2020/1/16 15:17
 * @Version 1.0
 */
@Service
public class TransformServiceImpl implements TransformService {
    @Autowired
    private TransformMapper transformMapper;

    @Override
    public void exportTransform(String idCard) throws Exception {
        TransformEntity transform = transformMapper.queryTransform(idCard);
        String sex = Utils.querySexByIdcard(transform.getIdCard()).equals("m") ? "男" : "女";
        int age = Utils.calcAgeByBirthday(transform.getIdCard());
        //户籍
        String register = transformMapper.getRegister(transform.getIdCard().substring(0, 6));
        if (Utils.isEmpty(register)) {
            register = "";
        }
        //紧急联系人
        List<Map> emergency = Utils.stringToList(transform.getEmergencyContact(), Map.class);
        String emergencyContact = "";
        String relation = "";
        String emergencyPhone = "";
        if (Utils.isNotEmpty(emergency)) {
            emergencyContact = (String) emergency.get(0).get("name");
            relation = (String) emergency.get(0).get("ralative");
            emergencyPhone = (String) emergency.get(0).get("phone");
        }

        String basePath = "D://transform";
        File pathFile = new File(basePath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        String fileName = "apply_" + UUID.randomUUID().toString() + ".doc";
        String filePath = basePath + File.separator + fileName;
        File sourceFile = ResourceUtils.getFile(getClass().getClassLoader().getResource("").getPath() + "/apply.doc");
        FileUtil.copyFile(sourceFile.getPath(), filePath);
        License license = new License();
        license.setLicense(License.class.getResourceAsStream("/resources/aspose.word.license.xml"));
        Document document = new Document(filePath);
        //身份特征
        List<String> identity = Utils.stringToList(transform.getIdentity(), String.class);
        String[] identityArr = {"散居特困老人", "低保老人", "低保边缘老人", "计划生育特殊老人", "其他"};
        String[] identityBase = {"destitute", "allowance", "edge", "birthControl", "other"};
        List<String> identityResult = convertData(identity, document, identityArr, identityBase);
        //申请改造项目
        List<String> applyItem = Utils.stringToList(transform.getApplyItem(), String.class);
        String[] applyItemArr = {"建筑硬件改造", "家具家装改造", "康复辅助器具适配", "智能化助老服务设施配备"};
        String[] applyItemBase = {"architecture", "furniture", "recure", "serve"};
        List<String> applyItemResult = convertData(applyItem, document, applyItemArr, applyItemBase);
        //数据填充
        String[] fieldArray = new String[]{
                "name", "sex", "register", "idCard", "age",
                "address", "mobile", "phone", "contact", "contactMobile",
                "relation", "content", "cause", "operator", "sign",
                "destitute", "allowance", "edge", "birthControl", "other",
                "architecture", "furniture", "recure", "serve"};
        Object[] dataArray = new Object[]{
                transform.getUserName(), sex, register, transform.getIdCard(), age,
                transform.getAddressDetail(), transform.getPhone(), transform.getPhone(), emergencyContact, emergencyPhone,
                relation, transform.getTransformContents(), transform.getTransformCause(), transform.getOperator(), transform.getUserName(),
                identityResult.get(0), identityResult.get(1), identityResult.get(2), identityResult.get(3), identityResult.get(4),
                applyItemResult.get(0), applyItemResult.get(1), applyItemResult.get(2), applyItemResult.get(3)};
        document.getMailMerge().execute(fieldArray, dataArray);
        document.save(filePath);
    }

    @Override
    public void exportTransformScheme(String idCard) throws Exception {
        String basePath = "D://transform";
        File pathFile = new File(basePath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        String fileName = UUID.randomUUID().toString() + ".doc";
        String filePath = basePath + File.separator + fileName;
        File sourceFile = ResourceUtils.getFile(getClass().getClassLoader().getResource("").getPath() + "/scheme.doc");
        FileUtil.copyFile(sourceFile.getPath(), filePath);
        License license = new License();
        license.setLicense(License.class.getResourceAsStream("/resources/aspose.word.license.xml"));
        Document document = new Document(filePath);
        //数据填充
        String[] fieldArray = new String[]{"name", "idCard", "address", "contact"};
        Object[] dataArray = new Object[]{"姓名", "身份证", "地址", "联系人"};
        document.getMailMerge().execute(fieldArray, dataArray);

        //循环信息
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        CommonWordDataSource dataSource = new CommonWordDataSource(dataList, "scheme");
        for (int i = 0; i < 3; i++) {
            Map<String, Object> data = new HashMap<String, Object>(4);
            data.put("content", "内容" + i);
            data.put("shop", "供应商" + i);
            data.put("time", "时间" + i);
            data.put("sign", "签字" + i);
            dataList.add(data);
        }
        document.getMailMerge().executeWithRegions(dataSource);

        //照片
        DocumentBuilder builder = new DocumentBuilder(document);
        builder.moveToMergeField("before");
        String imgPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()
                + "static/img/bg1.jpg";
        builder.insertImage(imgPath.substring(1), RelativeHorizontalPosition.MARGIN, 0, RelativeVerticalPosition.MARGIN,
                1, 300, 150, WrapType.SQUARE);

        document.save(filePath);
    }

    private List<String> convertData(List<String> identity, Document document, String[] arr, String[] base) throws Exception {
        List<String> result = new ArrayList<>(5);
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < identity.size(); i++) {
                if (identity.get(i).equals(arr[j])) {
                    result.add("√");
                    DocumentBuilder builder = new DocumentBuilder(document);
                    builder.moveToMergeField(base[i]);
                    builder.getParagraphFormat().setStyleIdentifier(StyleIdentifier.BODY_TEXT);
                    builder.getFont().setName("Wingdings 2");
                    builder.getFont().setSize(15);
                    builder.write("R");
                    break;
                }
            }
            result.add("□");
        }
        return result;
    }
}
