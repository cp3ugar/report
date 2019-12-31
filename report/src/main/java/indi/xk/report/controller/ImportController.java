package indi.xk.report.controller;

import indi.xk.report.service.ImportService;
import indi.xk.report.utils.BaseRuntimeException;
import indi.xk.report.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author xk
 * @Date 2019/12/23 14:27
 * @Version 1.0
 */
@Controller
public class ImportController extends BaseController{
    @Autowired
    private ImportService importService;

    @PostMapping("/import")
    @ResponseBody
    public ReturnObject<String> importStudent(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            if (null == file || file.getSize() == 0) {
                throw new BaseRuntimeException(400, "文件不能为空！");
            }
            return new ReturnObject(importService.importStudent(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnObject.outSuccess("导入失败！");
    }

    @PostMapping("/importLitigation")
    @ResponseBody
    public ReturnObject importLitigation(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        importService.importLitigation(is);
        return ReturnObject.outSuccess("导入成功！");
    }
}
