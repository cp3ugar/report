package indi.xk.report.controller;

import indi.xk.report.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author xk
 * @Date 2019/12/23 14:27
 * @Version 1.0
 */
public class ImportController {
    @Autowired
    private ImportService importService;

    @RequestMapping("/import")
    @ResponseBody
    public String importExcel(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        importService.importExcel(is);
        return "导入成功！";
    }
}
