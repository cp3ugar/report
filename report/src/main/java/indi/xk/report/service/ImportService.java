package indi.xk.report.service;

import indi.xk.report.pojo.dto.StudentDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/23 16:46
 * @Version 1.0
 */
public interface ImportService {
    void importExcel(InputStream is) throws IOException;
}
