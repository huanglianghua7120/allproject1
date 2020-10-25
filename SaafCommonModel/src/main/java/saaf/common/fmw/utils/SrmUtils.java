package saaf.common.fmw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SrmUtils {

    /**
     * 按指定格式，格式化日期
     * @param date 日期
     * @param format 格式
     * @return
     */
    public static String getDateStr(Date date, String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        return f.format(date);
    }

    /**
     * 加减天数
     *
     * @param date
     * @param value
     * @return
     */
    public static Date dateAdd(Date date, Integer value) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, value); // 今天   加 value   天
        Date date2 = c.getTime();
        return date2;
    }

    /**
     * 加减天数
     *
     * @param dateStr
     * @param value
     * @return
     */
    public static String dateAdd(String dateStr, Integer value) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = f.parse(dateStr);
            Date date2 = dateAdd(date, value);
            return f.format(date2);
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return null;

    }

    /**
     * 判断指定对象是否为NULL，为NULL则返回true，否则返回false
     * @param value
     * @return
     */
    public static boolean isNvl(Object value) {
        if (value != null && !"".equals(value.toString().trim())) {
            return false;
        }
        return true;
    }

    /**
     * 转换字符串
     * @param value
     * @return
     */
    public static String getString(Object value) {
        if (value == null || "".equals(value.toString().trim())) {
            return null;
        }
        return value.toString();
    }

    /**
     * 判断两个对象是否一致
     * @param o1
     * @param o2
     * @return
     */
    public static boolean compareValueSame(Object o1, Object o2) {
        if (isNvl(o1) || isNvl(o2)) {
            return false;
        }
        if (o1.toString().equals(o2.toString())) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean compareSame(Object o1, Object o2) {
        if (isNvl(o1) && isNvl(o2)) {
            return true;
        }
        if (o1 != null && o2 != null && o1.toString().equals(o2.toString())) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param obj
     * @return
     */
    public static String object2String(Object obj) {
        if (obj == null || "null".equals(obj + "")) {
            return "";
        }
        return obj.toString();

    }

    /**
     *
     * @param passU9Flag
     * @return
     */
    public static String getUpadteU9Flag(String passU9Flag) {
        if (passU9Flag == null || "N".equals(passU9Flag)) {
            return "N";
        }
        return "U";
    }

    /**
     * 获取当前日期+时间，返回格式为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentDateStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 获取当前日期，返回格式为yyyy-MM-dd
     * @return
     */
    public static Date getCurrentDate() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String str = f.format(new Date());

        Date date = new Date();
        try {
            date = f.parse(str);
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断指定字符串是否包含SQL关键字及字符，如果包含，则返回ture，否则返回false
     * @param str
     * @return
     */
    public static boolean isContainSQL(String str) {
        if (isNvl(str)) {
            return false;
        }
        //统一转为小写
        String strValue = str.toLowerCase();
        //过滤掉的sql关键字，可以手动添加
        String badStr = "and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|net user|xp_cmdshell|;|or|--|+|like|create|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|order|by|//|/|#";
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (strValue.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "APPROVED,INTRODUCING,EFFETIVE,QUIT";
        System.out.println("AAAA:" + SrmUtils.isContainSQL(str));
    }
}
