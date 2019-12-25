package indi.xk.report.utils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

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

    /**
     * 非空
     * @param
     * @return
     * @author xk
     * @date 2019/12/23  16:39
     */
    public static boolean isNotEmpty(Object obj) {
        return (!(isEmpty(obj)));
    }

    /**
     * 空
     * @param
     * @return
     * @author xk
     * @date 2019/12/23  16:40
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            if (((String) o).trim().length() == 0) {
                return true;
            }
        } else if (o instanceof Collection) {
            if (((Collection<?>) o).isEmpty()) {
                return true;
            }
        } else if (o.getClass().isArray()) {
            if (((Object[]) (Object[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map<?, ?>) o).isEmpty()) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
}
