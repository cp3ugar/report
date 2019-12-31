package indi.xk.report.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

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

    /**
     * date转string
     * @param date
     * @return
     */
    public static String getStringDate(Date date,String pattern) {
        if(Utils.isEmpty(date)){
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        return dateString;
    }

    public static File getCopyFile(String localPath, String path, InputStream inputStream) {
        File targetFile = null;
        try {
            File f = new File(localPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            FileUtils.copyToFile(inputStream, targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return targetFile;
    }

    /**
     * 验证日期格式
     * @param str
     * @param formatString
     * @return 返回验证结果
     */
    public static boolean validDate(String str, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 获取UUID
     * @param key
     * @return
     */
    public static String getUUid(String key) {
        String uuid = UUID.randomUUID().toString();
        String id = uuid.replaceAll("-", "");
        return key + id;
    }
}
