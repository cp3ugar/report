package indi.xk.report.controller;

import indi.xk.report.service.ImportService;
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
public class ImportController {
    @Autowired
    private ImportService importService;

    @PostMapping("/import")
    @ResponseBody
    public ReturnObject importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        importService.importExcel(is);
        return ReturnObject.outSuccess("导入成功！");
    }
}
