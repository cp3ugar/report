package indi.xk.report.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author xk
 * @Date 2019/12/23 16:46
 * @Version 1.0
 */
@Component
public interface ImportService {
    void importExcel(InputStream is) throws IOException;
}
