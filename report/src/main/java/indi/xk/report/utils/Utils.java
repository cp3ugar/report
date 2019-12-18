package indi.xk.report.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author xk
 * @Date 2019/12/18 15:24
 * @Version 1.0
 */
public class Utils {
    /**
     * 获取当前时间
     * @param pattern
     * @return
     */
    public static String getNowTime(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }
}
