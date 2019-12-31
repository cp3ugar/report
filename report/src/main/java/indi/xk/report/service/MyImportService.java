package indi.xk.report.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zxy on 2019/12/30.
 */
@Component
public interface MyImportService {
    void importExceltoMysql(InputStream is) throws IOException;
}
