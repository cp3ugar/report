package indi.xk.report.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public static String querySexByIdcard(String idcard) {

        if (Utils.isEmpty(idcard)) {
            return "";
        }

        if (idcard.length() == 15) {

            String sexStr = idcard.substring(idcard.length() - 1, idcard.length());

            Integer sexInt = Integer.valueOf(sexStr);

            return sexInt % 2 == 0 ? "f" : "m";

        }

        if (idcard.length() == 18) {

            String sexStr = idcard.substring(idcard.length() - 2, idcard.length() - 1);

            Integer sexInt = Integer.valueOf(sexStr);

            return sexInt % 2 == 0 ? "f" : "m";

        }

        return "";
    }

    /**
     * string转list
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> List<T> stringToList(String data, Class<T> tClass) {
        if (isNotEmpty(data)) {
            return JSONObject.parseArray(data, tClass);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Object转string
     *
     * @param data
     * @return
     */
    public static String listToString(Object data) {
        if (isNotEmpty(data)) {
            return JSON.toJSONString(data);
        }else{
            return null;
        }
    }

    public static Date parseDate(String isDateString) {
        Date tcDate = null;
        if (isDateString != null && !isDateString.equals("")) {
            if (isDateString.contains(" ")) {
                if (isDateString.contains(":")) {
                    // 包含时间
                    try {
                        SimpleDateFormat tcDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        tcDate = tcDateFormat.parse(isDateString);
                        return tcDate;
                    } catch (Exception e) {

                    }
                    try {
                        SimpleDateFormat tcDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        tcDate = tcDateFormat.parse(isDateString);
                        return tcDate;
                    } catch (Exception e) {

                    }
                } else {
                    // 不包含时间
                    try {
                        SimpleDateFormat tcDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        tcDate = tcDateFormat.parse(isDateString);
                        return tcDate;
                    } catch (Exception e) {

                    }
                }
            } else {
                try {
                    SimpleDateFormat tcDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    tcDate = tcDateFormat.parse(isDateString);
                    return tcDate;
                } catch (Exception e) {

                }
                try {
                    SimpleDateFormat tcDateFormat = new SimpleDateFormat("yyyyMMdd");
                    tcDate = tcDateFormat.parse(isDateString);
                    return tcDate;
                } catch (Exception e) {

                }
                try {
                    SimpleDateFormat tcDateFormat = new SimpleDateFormat("公元yyyy年MM月dd日");
                    tcDate = tcDateFormat.parse(isDateString);
                    return tcDate;
                } catch (Exception e) {

                }

                try {
                    SimpleDateFormat tcDateFormat = new SimpleDateFormat("yyyy-MM");
                    tcDate = tcDateFormat.parse(isDateString);
                    return tcDate;
                } catch (Exception e) {

                }
                try {
                    SimpleDateFormat tcDateFormat = new SimpleDateFormat("HH:mm");
                    tcDate = tcDateFormat.parse(isDateString);
                    return tcDate;
                } catch (Exception e) {

                }
            }
        }
        return tcDate;
    }

    /**
     * 根据出生日期计算年龄
     *
     * @param date
     * @return
     */
    public static int calcAgeByBirthday(String date) {

        Date birthday = Utils.parseDate(date);

        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthday is before NOW.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }

        return age;

    }

    public static void main(String[] args) {
        System.out.println(calcAgeByBirthday("510122199306030526"));
    }
}
