package indi.xk.report.controller;

import indi.xk.report.service.TransformService;
import indi.xk.report.utils.BaseRuntimeException;
import indi.xk.report.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author xk
 * @Date 2020/1/16 15:15
 * @Version 1.0
 */
@RestController
public class TransformController {
    @Autowired
    private TransformService transformService;

    @GetMapping("/exportTransform")
    public void exportTransform(String idCard) throws Exception {
        if(Utils.isEmpty(idCard)){
            throw new BaseRuntimeException(400,"缺少身份证！");
        }
        transformService.exportTransform(idCard);
    }

    @GetMapping("/exportTransformScheme")
    public void exportTransformScheme(String idCard) throws Exception {
        if(Utils.isEmpty(idCard)){
            throw new BaseRuntimeException(400,"缺少身份证！");
        }
        transformService.exportTransformScheme(idCard);
    }

    @GetMapping("/convertPdfToWord")
    public void convertPdfToWord() throws Exception {
        transformService.convertPdfToWord();
    }
}
