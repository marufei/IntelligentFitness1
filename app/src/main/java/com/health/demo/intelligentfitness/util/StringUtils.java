package com.health.demo.intelligentfitness.util;


import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串操作工具包
 *
 * @author mfl(http://weibo.com/mengfanlujava)
 * @version 1.0
 * @created 2015-07-25
 */
public class StringUtils {
    private static final String TAG = "StringUtils";

    // 邮箱格式验证
    private final static Pattern emailer = Pattern
            .compile("^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");

    // 年月日时分秒：2015-07-25 17:50:34
    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    // 年月日：2015-07-25
    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    // 年月日时分：2015-07-25 17:50
    private final static ThreadLocal<SimpleDateFormat> dateFormater3 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
    };

    // 月日时分：07-25 17:50
    private final static ThreadLocal<SimpleDateFormat> dateFormater4 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd HH:mm");
        }
    };

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater2.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     */
    public static String friendly_time(String sdate) {
        Date time = toDate(sdate);
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 5) {
            ftime = days + "天前";
        } else if (days > 6) {
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     */
    public static String friendly_time2(String sdate) {
        Date time = toDate(sdate);
        if (time == null) {
            return "Unknown";
        }
        return dateFormater.get().format(time);
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 返回long类型的今天的日期
     *
     * @return
     */
    public static long getToday() {
        Calendar cal = Calendar.getInstance();
        String curDate = dateFormater2.get().format(cal.getTime());
        curDate = curDate.replace("-", "");
        return Long.parseLong(curDate);
    }

    /**
     * 返回String类型的今天的日期
     *
     * @return
     */
    public static String getTodayStr() {
        Date today = new Date();
        String nowDate = dateFormater2.get().format(today);
        return nowDate;
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);
        try {
            String line;
            line = read.readLine();
            while (line != null) {
                res.append(line);
                line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != isr) {
                    isr.close();
                    isr.close();
                }
                if (null != read) {
                    read.close();
                    read = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
            }
        }
        return res.toString();
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断今天是周几
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 字符串UTF-8编码
     *
     * @param source
     * @return
     */
    public static String getUTF8String(String source) {
        String UTF8Str = "";
        try {
            UTF8Str = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return UTF8Str;
    }

    /**
     * 验证手机格式
     * "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][34578]\\d{9}";
        if (isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    public static boolean isPhone(String phone) {
        String phomeRegex = "/^(0\\d{2,3})?\\d{7,9}$/g";
        if (isEmpty(phone)) return false;
        else return phomeRegex.matches(phomeRegex);

    }

    /**
     * 验证手机格式
     * "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
     */
    public static boolean isMobileNOIndex(String mobiles) {
        String telRegex = "[1][34578]";
        if (isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    public static String filterNULL(String str) {
        return str == null ? "" : str;
    }


    public static String replaceSubString(String str, int n) {
        String sub = "";
        try {
            sub = str.substring(0, str.length() - n);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < n; i++) {
                sb = sb.append("*");
            }
            sub += sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sub;
    }

    public static String idCardReplaceWithStar(String idCard) {

        if (TextUtils.isEmpty(idCard)) {
            return null;
        } else {
            return idCard.replaceAll("(?<=\\d{3})\\d(?=\\d{4})", "*");
        }
    }

    public static String bankCardReplaceWithStar(String idCard) {

        if (TextUtils.isEmpty(idCard)) {
            return null;
        } else {
            int lastNum = idCard.length() % 4;
            if (lastNum == 0) {
                lastNum = 4;
            }
            String bankCard = idCard.replaceAll("(?<=\\d{4})\\d(?=\\d{" + lastNum + "})", "*");

            return bankCard.replaceAll(".{4}(?!$)", "$0 ");
        }
    }

    public static String formatPrice(double price) {
        try {
            double newPrice = price;
            DecimalFormat df = new DecimalFormat("######0.00");
            double showPrice = newPrice / 100;
            return df.format(showPrice);
        } catch (Exception e) {
            MyUtils.Loge(TAG, e.getMessage());
            return null;
        }

    }

    public static String formatTime(String time) {
        if (time.length() == 1) {
            return "0" + time;
        } else {
            return time;
        }
    }

    ;

    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }


    public static boolean isSimplePwd(String pwd) {
        return Arrays.asList(arr).contains(pwd) || isIncreasing(pwd) || isDecline(pwd);
    }

    private static String[] arr = {"111111", "222222", "333333", "444444", "555555", "666666", "777777", "888888", "999999", "000000"};

    public static boolean isIncreasing(String pwd) {
        for (int i = 0; i < pwd.length() - 1; i++) {
            String s1 = pwd.substring(i, i + 1);
            String s2 = pwd.substring(i + 1, i + 2);
            if (Integer.parseInt(s2) - Integer.parseInt(s1) != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDecline(String pwd) {
        for (int i = 0; i < pwd.length() - 1; i++) {
            String s1 = pwd.substring(i, i + 1);
            String s2 = pwd.substring(i + 1, i + 2);
            if (Integer.parseInt(s1) - Integer.parseInt(s2) != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static BigDecimal mul1(double v1, double v2, int scale) {
        BigDecimal value = new BigDecimal(v1 * v2);
        value = value.setScale(scale, BigDecimal.ROUND_HALF_UP);
        System.out.println(value);
        return value;
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static BigDecimal div1(double v1, double v2, int scale) {

        BigDecimal value = new BigDecimal(v1 / v2);
        value = value.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return value;

    }

    /**
     * 金豆转换为人民币
     * @param s 金豆
     * @return 人民币
     */

    public static String JinDou2RMB(String s) {
        String s1 = null;
        s1 = String.valueOf(Integer.valueOf(s) / 100);
        return s1;
    }


}
