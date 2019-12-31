package indi.xk.report.controller;

/**
 * Created by zxy on 2019/12/30.
 */

import indi.xk.report.service.MyImportService;
import indi.xk.report.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class MyImportController {
    @Autowired
    private MyImportService myImportService;
    @ResponseBody
    @RequestMapping("import1")
    public ReturnObject importExceltoMysql(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream is=file.getInputStream();
        myImportService.importExceltoMysql(is);

    return ReturnObject.outSuccess("导入成功！");
    }
}
