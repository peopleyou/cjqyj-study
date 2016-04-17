package com.cjqyj.study.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author
 * @version 1.0
 * @since 2014-05-12 22:43
 * 功能说明：
 */
public class DateUtils {

    public static String CURR_DATE_STR = DateUtils.getDateFormat(new Date(), "yyyyMMdd");
    private static long lastUpdateTime = 0;

    /**
     * 数据库存储的时间格式串，如yyyymmdd 或yyyymmddHHMiSS
     */
    public static final int DB_STORE_DATE = 1;
    /**
     * 用连字符-分隔的时间时间格式串，如yyyy-mm-dd 或yyyy-mm-dd HH:Mi:SS
     */
    public static final int HYPHEN_DISPLAY_DATE = 2;
    /**
     * 用连字符.分隔的时间时间格式串，如yyyy.mm.dd 或yyyy.mm.dd HH:Mi:SS
     */
    public static final int DOT_DISPLAY_DATE = 3;
    /**
     * 用中文字符分隔的时间格式串，如yyyy年mm月dd 或yyyy年mm月dd HH:Mi:SS
     */
    public static final int CN_DISPLAY_DATE = 4;

    /**
     * 用中文字符分隔的时间格式串 yyyy-mm-dd HH:Mi:SS
     */
    public static final int CN_DISPLAY_DATE_M = 6;

    /**
     * **数据库存储时间格式串,如yyyymmddHHMissSS*
     */
    public static final int DB_LONG_DATE = 5;

    /**
     * 得到精确到秒的格式化当前时间串
     *
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE}
     * @return 当前时间格式化时间串
     */
    public static String getCurrTimeStr(int formatType) {
        return getTimeStr(new Date(), formatType);
    }

    /**
     * 返回数据存在格式的当前时间，格式为或yyyymmddHHMiSS
     *
     * @return
     */
    public static String getCurrTimeStrOfStoreFormat() {
        return getCurrTimeStr(DB_STORE_DATE);
    }

    /**
     * 返回数据存在格式的当前时间，格式为或yyyymmdd
     *
     * @return
     */
    public static String getCurrDateStrOfStoreFormat() {
        return getCurrDateStr(DB_STORE_DATE);
    }

    /**
     * 得到精确到秒的格式化时间串
     *
     * @param date       指定时间
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE}
     * @return 指定时间的格式化时间串
     */
    public static String getTimeStr(Date date, int formatType) {
        if (formatType < DB_STORE_DATE || formatType > DB_LONG_DATE) {
            throw new IllegalArgumentException("时间格式化类型不是合法的值。");
        } else {
            String formatStr = null;
            switch (formatType) {
                case DB_STORE_DATE:
                    formatStr = "yyyyMMddHHmmss";
                    break;
                case HYPHEN_DISPLAY_DATE:
                    formatStr = "yyyy'-'MM'-'dd HH:mm:ss";
                    break;
                case DOT_DISPLAY_DATE:
                    formatStr = "yyyy.MM.dd HH:mm:ss";
                    break;
                case CN_DISPLAY_DATE:
                    formatStr = "yyyy'年'MM'月'dd'日' HH:mm:ss";
                    break;
                case DB_LONG_DATE:
                    formatStr = "yyyyMMddHHmmssSSS";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            return sdf.format(date);
        }
    }

    /**
     * 得到精确到天的当前格式化日期串
     *
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE}
     * @return
     */
    public static String getCurrDateStr(int formatType) {
        return getDateStr(new Date(), formatType);
    }

    /**
     * 得到精确到天的指定时间格式化日期串
     *
     * @param date       指定时间
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE}
     * @return 指定时间格式化日期串
     */
    public static String getDateStr(Date date, int formatType) {

//        if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
//            throw new IllegalArgumentException("时间格式化类型不是合法的值。");
//        } else {
        String formatStr = null;
        switch (formatType) {
            case DB_STORE_DATE:
                formatStr = "yyyyMMdd";
                break;
            case HYPHEN_DISPLAY_DATE:
                formatStr = "yyyy-MM-dd";
                break;
            case DOT_DISPLAY_DATE:
                formatStr = "yyyy.MM.dd";
                break;
            case CN_DISPLAY_DATE:
                formatStr = "yyyy'年'MM'月'dd'日'";
                break;
            case CN_DISPLAY_DATE_M:
                formatStr = "yyyy-MM-dd HH:mm:ss";
                break;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
//        }
    }

    /**
     * 判断 strDate 是否在 beginDate 和 endDate之间
     *
     * @param strDate   YYYY-MM-DD
     * @param beginDate
     * @param endDate
     * @return
     */
    public static boolean compareDate(String strDate, Date beginDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bdate = null;
        Date edate = null;
        Date d0 = null;
        try {
            bdate = sdf.parse(sdf.format(beginDate));
            edate = sdf.parse(sdf.format(endDate));
            d0 = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (bdate.getTime() <= d0.getTime() && edate.getTime() >= d0.getTime());
    }

    /**
     * 得到精确到月的当前时间格式化年月串
     *
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE}
     * @return 精确到月当前时间格式化年月串
     */
    public static String getYearMonthStr(int formatType) {
        return getYearMonthStr(new Date(), formatType);
    }

    /**
     * 得到精确到月的指定时间格式化年月串
     *
     * @param date       指定的时间
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE}
     * @return 精确到月当前时间格式化年月串
     */
    public static String getYearMonthStr(Date date, int formatType) {
        if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
            throw new IllegalArgumentException("时间格式化类型不是合法的值。");
        } else {
            String formatStr = null;
            switch (formatType) {
                case DB_STORE_DATE:
                    formatStr = "yyyyMM";
                    break;
                case HYPHEN_DISPLAY_DATE:
                    formatStr = "yyyy-MM";
                    break;
                case DOT_DISPLAY_DATE:
                    formatStr = "yyyy.MM";
                    break;
                case CN_DISPLAY_DATE:
                    formatStr = "yyyy'年'MM'月'";
                    break;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            return sdf.format(date);
        }
    }

    /**
     * 将数据库存储的日期格式串转换为各种显示的格式
     *
     * @param dateStr    最小6位，最大17位的数据库存储格式时间串如:20041212
     * @param formatType 时间格式的类型{@link #DB_STORE_DATE}
     * @return 格式化的时间串
     */
    public static String toDisplayStr(String dateStr, int formatType) {
        if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
            throw new IllegalArgumentException("时间格式化类型不是合法的值。");
        }
        if (dateStr == null || dateStr.length() < 6 || dateStr.length() > 14 || formatType == DB_STORE_DATE) {
//            return StringUtils.toVisualString(dateStr);
            return dateStr;
        } else {
            char[] charArr = null;
            switch (formatType) {
                case HYPHEN_DISPLAY_DATE:
                    charArr = new char[]{'-', '-', ' ', ':', ':'};
                    break;
                case DOT_DISPLAY_DATE:
                    charArr = new char[]{'.', '.', ' ', ':', ':'};
                    break;
                case CN_DISPLAY_DATE:
                    charArr = new char[]{'年', '月', '日', ':', ':'};
                    break;
                default:
                    charArr = new char[]{'-', '-', ' ', ':', ':'};
            }
            try {
                SimpleDateFormat sdf_1 = null;
                SimpleDateFormat sdf_2 = null;
                switch (dateStr.length()) {
                    case 6:
                        sdf_1 = new SimpleDateFormat("yyyyMM");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM");
                        break;
                    case 7:
                        sdf_1 = new SimpleDateFormat("yyyyMMd");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'" + charArr[1] + "'d");
                        break;
                    case 8:
                        sdf_1 = new SimpleDateFormat("yyyyMMdd");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'" + charArr[1] + "'dd" + charArr[2]);
                        break;
                    case 10:
                        sdf_1 = new SimpleDateFormat("yyyyMMddHH");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'" + charArr[1] + "'dd'" + "+charArr[2]"
                                + "'HH");
                        break;
                    case 12:
                        sdf_1 = new SimpleDateFormat("yyyyMMddHHmm");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'" + charArr[1] + "'dd'" + charArr[2]
                                + "'HH'" + charArr[3] + "'mm");
                        break;
                    case 14:
                        sdf_1 = new SimpleDateFormat("yyyyMMddHHmmss");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'" + charArr[1] + "'dd'" + charArr[2]
                                + "'HH'" + charArr[3] + "'mm'" + charArr[4] + "'ss");
                        break;
                    case 17:
                        sdf_1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                        sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'" + charArr[1] + "'dd'" + charArr[2]
                                + "'HH'" + charArr[3] + "'mm'" + charArr[4] + "'ss SSS");
                        break;
                    default:
                        return dateStr.trim();
                }
                return sdf_2.format(sdf_1.parse(dateStr)).trim();
            } catch (ParseException ex) {
                return dateStr.trim();
            }
        }
    }

    /**
     * 将显示格式的时间字符串转换为数据库存储的类型
     *
     * @param dateStr 最小4位，最大19位。显示的时间格式时间串如:2004-12-12
     * @return 数据库存储的时间字符串
     */
    public static String toStoreStr(String dateStr) {
        if (dateStr == null || dateStr.trim().equals("")) {
            return "";
        }
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < dateStr.length(); i++) {
            if (dateStr.charAt(i) >= '0' && dateStr.charAt(i) <= '9') {
                strBuf.append(dateStr.charAt(i));
            }
        }
        return strBuf.toString();
    }

    /**
     * <b>功能描述：</b> 把时间转换成14位的字符串，不足位数补充 0 或者 9
     *
     * @param dateStr
     * @param bMax
     * @return
     */
    public static String toStoreStr14(String dateStr, boolean bMax) {
        if (dateStr == null || dateStr.trim().equals("")) {
            return "";
        }
        StringBuffer strBuf = new StringBuffer(toStoreStr(dateStr));
        String retStr = strBuf.toString();
        if (bMax) {
            retStr = rightPad(retStr, 14, '9');
        } else {
            retStr = rightPad(retStr, 14, '0');
        }
        return retStr;
    }

    public static String rightPad(final String str, final int size, final char padChar) {
        if (str == null) {
            return null;
        }
        final int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }

        for (int i = 0; i < pads; i++) {
            str.concat(String.valueOf(padChar));
        }
        return str;
    }

    /**
     * 将生日存储的时间格式转化为年龄（周岁，小数点后不计）
     *
     * @param birthdayStr 生日字段 "yyyymmdd"
     * @return 年龄
     */
    public static String birthdayToAge(String birthdayStr) {
        if (birthdayStr == null || birthdayStr.length() < 6) {
            return "";
        } else {
            int birthYear = Integer.parseInt(birthdayStr.substring(0, 4));
            int birthMonth = Integer.parseInt(birthdayStr.substring(4, 6));
            Calendar cal = new GregorianCalendar();
            int currYear = cal.get(Calendar.YEAR);
            int currMonth = cal.get(Calendar.MONTH);
            int age = currYear - birthYear;
            age -= (currMonth < birthMonth) ? 1 : 0;
            return "" + age;
        }
    }

    /**
     * 功能描述: 将年龄转换为生日
     *
     * @param age int
     * @return String
     */
    public static String ageToBirthday(int age) {

        String currDateStr = DateUtils.getYearMonthStr(DB_STORE_DATE);
        int currDateInt = Integer.parseInt(currDateStr);
        int ageDateInt = currDateInt - age * 100;
        return String.valueOf(ageDateInt);
    }

    /**
     * 功能描述: 增加一年
     *
     * @param birDate
     * @return
     */
    public static String nextAgeDate(String birDate) {

        int currDateInt = Integer.parseInt(birDate);
        int ageDateInt = currDateInt + 100;
        return String.valueOf(ageDateInt);
    }

    /**
     * @param dateTimeStr String 格式化的时间串
     * @param formatType  数据格式类型 {@link #DB_STORE_DATE}
     * @param detal       int 增加或减少的时间
     * @param field       int 参见Calendar中关于时间字段属性的定义
     * @return String 返回的
     */
    public static String add(String dateTimeStr, int formatType, int detal, int field) {
        if (dateTimeStr == null || dateTimeStr.length() < 6) {
            return dateTimeStr;
        } else {
            try {
                String formatStr = null;
                switch (formatType) {
                    case DB_STORE_DATE:
                        formatStr = "yyyyMMddHHmmss";
                        break;
                    case HYPHEN_DISPLAY_DATE:
                        formatStr = "yyyy-MM-dd HH:mm:ss";
                        break;
                    case DOT_DISPLAY_DATE:
                        formatStr = "yyyy.MM.dd HH:mm:ss";
                        break;
                    case CN_DISPLAY_DATE:
                        formatStr = "yyyy'年'MM'月' HH：mm：ss";
                        break;
                    default:
                        formatStr = "yyyyMMddHHmmss";
                        break;
                }

                formatStr = formatStr.substring(0, dateTimeStr.length());
                SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
                Date d = sdf.parse(dateTimeStr);
                GregorianCalendar g = new GregorianCalendar();
                g.setTime(d);
                g.add(field, detal);
                d = g.getTime();
                return sdf.format(d);
            } catch (ParseException ex) {
                ex.printStackTrace();
                return dateTimeStr;
            }
        }
    }

    /**
     * 时间戳转化为时间
     *
     * @param timestamp
     * @return
     */
    public static Date timestampToDate(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(timestamp);
        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期、时间格式化
     *
     * @param date   Date 将要被格式化的日期对象
     * @param outFmt String 返回样式，参照类说明，如：yyyy年MM月dd日
     * @return String 格式化后的日期、时间字符串，data为null时返回null，outFmt非法时返回yyyyMMdd格式
     */
    public static String getDateFormat(Date date, String outFmt) {
        if (null == date) {
            return null;
        }
        if (null == outFmt || "".equals(outFmt.trim())) { // outFmt非法
            outFmt = "yyyyMMdd";
        }

        String retu = null;
        SimpleDateFormat dateFormat = null;
        try {
            dateFormat = new SimpleDateFormat(outFmt);
        } catch (IllegalArgumentException iaex) { // outFmt非法
            dateFormat = new SimpleDateFormat("yyyyMMdd");
        }
        retu = dateFormat.format(date);

        dateFormat = null;

        return retu;
    }

    /**
     * 把日期时间对象格式化为yyyyMMdd样式
     *
     * @param date Date 将要被格式化的日期对象
     * @return String 格式化后的日期、时间字符串，如：20041001
     */
    public static String getDateFormat(Date date) {
        return getDateFormat(date, "yyyyMMdd");
    }

    /**
     * 把系统当前日期时间格式化为指定的样式
     *
     * @param outFmt String 返回样式，参照类说明，如：yyyy年MM月dd日
     * @return String 格式化后的日期、时间字符串，如：2004年10月01日
     */
    public static String getDateFormat(String outFmt) {
        return getDateFormat(new Date(), outFmt);
    }

    /**
     * 把系统当前日期时间格式化为默认样式yyyyMMdd
     *
     * @return String 格式化后的日期、时间字符串，如：20041001
     */
    public static String getDateFormat() {
        return getDateFormat(new Date(), "yyyyMMdd");
    }

    /**
     * 日期、时间格式化
     *
     * @param millis long the number of milliseconds（毫秒） since January 1, 1970,
     *               00:00:00 GMT.
     * @param outFmt String 返回样式，参照类说明，如：yyyy年MM月dd日
     * @return String 格式化后的日期、时间字符串
     */
    public static String getDateFormat(long millis, String outFmt) {
        Date d = new Date(millis);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        String retu = getDateFormat(calendar.getTime(), outFmt);
        calendar = null;
        return retu;
    }

    /**
     * 日期、时间格式化
     *
     * @param datestr String 存在一定格式的日期、时间字符串，如：20041001、200410011503
     * @param inFmt   String 对datestr参数格式说明，参照类说明，如：yyyyMMdd、yyyyMMddHHmm
     * @param outFmt  String 返回样式，参照类说明，如：yyyy年MM月dd日
     * @return String 格式化后的日期、时间字符串，如：2004年10月01日、2004年10月01日
     * <p/>
     * <p/>
     * <p/>
     * <BR>
     * 输出样式outFmt非法时，使用yyyyMMdd格式输出
     * @throws java.text.ParseException 当datestr不能格式化为inFmt格式时抛出此异常
     */
    public static String getDateFormat(String datestr, String inFmt, String outFmt) throws ParseException {
        if (null == datestr || "".equals(datestr.trim())) {
            return datestr;
        }

        if (null == inFmt || "".equals(inFmt.trim())) {
            return datestr;
        }

        if (null == outFmt || "".equals(outFmt.trim())) { // 输出样式非法
            outFmt = "yyyyMMdd";
        }

        Date inDate = getDate(datestr, inFmt);

        if (null == inDate) { // 根据inFmt分析datestr时抛出异常

            return datestr;
        }

        String retu = getDateFormat(inDate, outFmt);
        inDate = null;
        return retu;
    }

    /**
     * 把日期时间字符串，按inFmt样式转化为日期对象，然后格式化为默认样式yyyyMMdd
     *
     * @param datestr String 存在一定格式的日期、时间字符串，如：20041001、200410011503
     * @param inFmt   String 对datestr参数格式说明，参照类说明，如：yyyyMMdd、yyyyMMddHHmm
     * @return String 格式化后的日期、时间字符串，如：20041001、20041001
     * @throws java.text.ParseException 当datestr不能格式化为inFmt格式时抛出此异常
     */
    public static String getDateFormat(String datestr, String inFmt) throws ParseException {
        return getDateFormat(datestr, inFmt, "yyyyMMdd");
    }

    /**
     * 根据inFmt的样式，日期时间字符串转化为日期时间对象
     *
     * @param datestr String 日期时间字符串，如：20041001、2004年10月01日 15:03
     * @param format  String 对datestr参数格式说明，参照类说明，如yyyyMMdd、yyyy年MM月dd日 HH:mm
     * @return Date 日期时间对象，格式inFmt非法时，使用yyyyMMdd格式
     * @throws java.text.ParseException 当datestr不能格式化为inFmt格式时抛出此异常
     */
    public static Date getDate(String datestr, String format) {
        if (datestr == null || datestr.isEmpty()) {
            return null;
        }
        if (format == null || format.isEmpty()) { // inFmt非法
            format = "yyyyMMdd";
        }

        try {
            // 依据inFmt格式把日期字符串转化为日期对象

            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(true);
            return dateFormat.parse(datestr);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 获取指定日期的开始时间00:00:00
     * @param theDate
     * @return
     */
    public static Date getDateBegin(Date theDate) {
        if (theDate == null) {
            return null;
        }

        String strDate = DateUtils.getDateFormat(theDate, "yyyy-MM-dd");
        strDate += " 00:00:00";

        return DateUtils.getDate(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取指定日期的结束时间23:59:59
     * @param theDate
     * @return
     */
    public static Date getDateEnd(Date theDate) {
        if (theDate == null) {
            return null;
        }

        String strDate = DateUtils.getDateFormat(theDate, "yyyy-MM-dd");
        strDate += " 23:59:59";

        return DateUtils.getDate(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 对日期时间对象进行调整，实现如昨天是几号，去年的今天星期几等. <BR>
     * 例子：
     * <p/>
     * <p/>
     * <p/>
     * <pre>
     * &lt;blockquote&gt;
     * 计算去年今天星期几
     *
     * Date date = DateUtils.addDate(new Date(),Calendar.YEAR,-1);
     * System.out.println(DateUtils.getDateFormat(date,&quot;E&quot;));
     * 打印60天后是什么日期，并显示为 yyyy-MM-dd 星期
     * Date date = DateUtils.addDate(new Date(),Calendar.DATE,60);
     * System.out.println(DateUtils.getDateFormat(date,&quot;yyyy-MM-dd E&quot;));
     * &lt;/blockquote&gt;
     * </pre>
     *
     * @param date          Date 需要调整的日期时间对象
     * @param CALENDARFIELD int 对日期时间对象以什么单位进行调整：
     *                      <p/>
     *                      <pre>
     *                                                                                                                                                                                              &lt;blockquote&gt;
     *                                                                                                                                                                                              年 Calendar.YEAR
     *                                                                                                                                                                                              月 Calendar.MONTH
     *                                                                                                                                                                                              日 Calendar.DATE
     *                                                                                                                                                                                              时 Calendar.HOUR
     *                                                                                                                                                                                              分 Calendar.MINUTE
     *                                                                                                                                                                                              秒 Calendar.SECOND
     *                                                                                                                                                                                              &lt;/blockquote&gt;
     *                                                                                                                               </pre>
     * @param amount        int 调整数量，>0表向后调整（明天，明年），<0表向前调整（昨天，去年）
     * @return Date 调整后的日期时间对象
     */
    public static Date addDate(Date date, int CALENDARFIELD, int amount) {
        if (null == date) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(CALENDARFIELD, amount);
        return calendar.getTime();
    }

    /**
     * 对日期时间对象进行调整.
     *
     * @param datestr       String 需要调整的日期时间字符串，它的格式为yyyyMMdd
     * @param CALENDARFIELD int 对日期时间对象以什么单位进行调整
     * @param amount        int 调整数量
     * @return Date 调整后的日期时间对象
     * @throws java.text.ParseException 当datestr不能格式化为yyyyMMdd格式时抛出此异常
     * @see #addDate(java.util.Date, int, int)
     */
    public static Date addDate(String datestr, int CALENDARFIELD, int amount) throws ParseException {
        return addDate(getDate(datestr, "yyyyMMdd"), CALENDARFIELD, amount);
    }

    /**
     * 根据出生日期，计算出在某一个日期的年龄
     *
     * @param birthday Date 出生日期时间对象
     * @param date2    Date 计算日期对象
     * @return int 返回date2那一天出生日期为birthday的年龄，如果birthday大于date2则返回-1
     */
    public static int getAge(Date birthday, Date date2) {
        if (null == birthday || null == date2) {
            return -1;
        }

        if (birthday.after(date2)) { // birthday大于date2
            return -1;
        }

        int ibdYear = Integer.valueOf(getDateFormat(birthday, "yyyy"), -1);
        int idate2Year = Integer.valueOf(getDateFormat(date2, "yyyy"), -1);

        if (ibdYear < 0 || idate2Year < 0) {
            return -1;
        }
        if (ibdYear > idate2Year) {
            return -1;
        }

        return idate2Year - ibdYear + 1;
    }

    /**
     * 根据出生日期，计算出当前的年龄
     *
     * @param birthday Date 出生日期时间对象
     * @return int 返回出生日期为birthday的年龄，如果birthday大于当前系统日期则返回-1
     */
    public static int getAge(Date birthday) {
        return getAge(birthday, new Date());
    }

    /**
     * 根据出生日期，计算出当前的年龄
     *
     * @param birthdaystr String 出生日期时间字符串，其格式一定为yyyyMMdd
     * @return int 返回出生日期为birthday的年龄，如果birthday大于当前系统日期则返回-1
     * @throws java.text.ParseException 当datestr不能格式化为yyyyMMdd格式时抛出此异常
     */
    public static int getAge(String birthdaystr) throws ParseException {
        return getAge(getDate(birthdaystr, "yyyyMMdd"));
    }

    /**
     * 获得最近三天的第一天日期
     *
     * @param format
     * @return
     */
    public static String getThreeDayAgoBegin(String format) {
        SimpleDateFormat simpleDateFormat = null;
        if (!"".equals(format.trim()) && null != format.trim()) {
            simpleDateFormat = new SimpleDateFormat(format);
        } else {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        return simpleDateFormat.format(addDate(new Date(), Calendar.DATE, -2));
    }

    /**
     * 获得当前日期与本周日相差的天数
     *
     * @return
     */
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    /**
     * 获得本周第一天的日期
     *
     * @param format
     * @return
     */
    public static String getCurrentWeekBegin(String format) {
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat simpleDateFormat = null;
        if (!"".equals(format.trim()) && null != format.trim()) {
            simpleDateFormat = new SimpleDateFormat(format);
        } else {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        return simpleDateFormat.format(cal.getTime());
    }

    /**
     * 获得本周星期日的日期
     *
     * @param format
     * @return
     */
    public static String getCurrentWeekEnd(String format) {
        SimpleDateFormat simpleDateFormat = null;
        if (!"".equals(format.trim()) && null != format.trim()) {
            simpleDateFormat = new SimpleDateFormat(format);
        } else {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        return simpleDateFormat.format(currentDate.getTime());
    }

    /**
     * 获取当前月份的第一天
     *
     * @param format
     * @return
     */
    public static String getCurrentMonthBegin(String format) {
        SimpleDateFormat simpleDateFormat = null;
        if (!"".equals(format.trim()) && null != format.trim()) {
            simpleDateFormat = new SimpleDateFormat(format);
        } else {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取当前月的最后一天
     *
     * @param format
     * @return
     */
    public static String getCurrentMonthEnd(String format) {
        SimpleDateFormat simpleDateFormat = null;
        if (!"".equals(format.trim()) && null != format.trim()) {
            simpleDateFormat = new SimpleDateFormat(format);
        } else {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获得指定月份的第一天
     *
     * @param strdate
     * @param inFrm
     * @param outFrm
     * @return
     * @throws java.text.ParseException
     */
    public static String getAssignedMonthBegin(String strdate, String inFrm, String outFrm) throws ParseException {
        Date date = getDate(strdate, inFrm);
        if ("".equals(outFrm.trim()) || null == outFrm.trim()) {
            outFrm = "yyy-MM-dd";
        }
        return getDateFormat(getDateFormat(date, "yyyy-MM") + "-01", "yyy-MM-dd", outFrm);
    }

    /**
     * 获得指定月份最后一天
     *
     * @param strdate
     * @param inFrm
     * @param outFrm
     * @return
     */
    public static String getAssignedMonthEnd(String strdate, String inFrm, String outFrm) {
        Date date = getDate(strdate, inFrm);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat simpleDateFormat = null;
        if (!"".equals(outFrm.trim()) && null != outFrm.trim()) {
            simpleDateFormat = new SimpleDateFormat(outFrm);
        } else {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取三个月前的第一天的日期
     *
     * @param format
     * @return
     */
    public static String getThreeMonthAgoBegin(String format) throws ParseException {
        Date threeMonthAgo = addDate(new Date(), Calendar.MONTH, -2);

        String threeMonthAgoStr = getDateFormat(threeMonthAgo, "yyyy-MM-dd");
        return getAssignedMonthBegin(threeMonthAgoStr, "yyyy-MM-dd", format);
    }

    /**
     * 得到几天前的时间
     *
     * @param d   指定日期
     * @param day 几天前
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d   指定日期
     * @param day 几天前
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 判断当前日期是否在数据上报日期的范围内
     *
     * @return
     * @throws java.text.ParseException
     */
    public static boolean isOverTimeOfReport() throws ParseException {
        String currentDateStr = getDateFormat(new Date(), "yyyyMMdd");
        String currentMonthBeginStr = getCurrentMonthBegin("yyyyMMdd");
        Date reportEndDate = addDate(currentMonthBeginStr, Calendar.DAY_OF_MONTH, 15);
        String reportEndDateStr = getDateStr(reportEndDate, DateUtils.DB_STORE_DATE);
        return Integer.parseInt(currentDateStr) >= Integer.parseInt(currentMonthBeginStr)
                && Integer.parseInt(currentDateStr) <= Integer.parseInt(reportEndDateStr);
    }

    /**
     * 判断当前时期是否在数据上报日期的范围内
     *
     * @param currentDateStr
     * @return
     * @throws java.text.ParseException
     */
    public static boolean isOverTimeOfReport(String currentDateStr) throws ParseException {
        String currentMonthBeginStr = getCurrentMonthBegin("yyyyMMdd");
        Date reportEndDate = addDate(currentMonthBeginStr, Calendar.DAY_OF_MONTH, 15);
        String reportEndDateStr = getDateStr(reportEndDate, DateUtils.DB_STORE_DATE);
        return Integer.parseInt(currentDateStr) >= Integer.parseInt(currentMonthBeginStr)
                && Integer.parseInt(currentDateStr) <= Integer.parseInt(reportEndDateStr);
    }

    /**
     * 获取数据库中的时间值，并转换为系统后台展示的时间格式
     *
     * @param inputStr
     * @return
     * @throws java.text.ParseException
     */
    public static String getBackgroundDateFormat(String inputStr) throws ParseException {
        return DateUtils.getDateFormat(inputStr, "yyyyMMddHHmmss", "yyyy'-'MM'-'dd");
    }

    /**
     * 获取数据库中的时间值，并转换为系统后台展示的时间格式
     *
     * @param inputStr
     * @return
     * @throws java.text.ParseException
     */
    public static String getBackgroundTimeFormat(String inputStr) throws ParseException {
        return DateUtils.getDateFormat(inputStr, "yyyyMMddHHmmss", "yyyy'-'MM'-'dd hh':'mm':'ss");
    }

    /**
     * 获取数据库中的毫秒级时间值，并转换为系统后台展示的时间格式
     *
     * @param inputStr
     * @return
     * @throws java.text.ParseException
     */
    public static String getBackgroundMillisecondFormat(String inputStr) {
        try {
            return DateUtils.getDateFormat(inputStr, "yyyyMMddHHmmssSSS", "yyyy'-'MM'-'dd hh':'mm':'ss SSS");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStr;
    }

    /**
     * 获取数据库中的时间值，并转换成系统前台展示的事件格式
     *
     * @param inputStr
     * @return
     * @throws java.text.ParseException
     */
    public static String getForegroundDateFormat(String inputStr) {
        try {
            return DateUtils.getDateFormat(inputStr, "yyyyMMddHHmmss", "yyyy'-'MM'-'dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inputStr;
    }

    /**
     * 获得当前所在周的开始时间和结束时间
     *
     * @return
     */
    public static String[] getCurrentWeek() {
        GregorianCalendar cal = new GregorianCalendar();
        Date now = new Date();
        cal.setTime(now);
        cal.setFirstDayOfWeek(GregorianCalendar.MONDAY); // 设置一个星期的第一天为星期1，默认是星期日

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("now=" + simpleDateFormat.format(cal.getTime())); // 今天

        cal.add(GregorianCalendar.DATE, -1);
        System.out.println("now=" + simpleDateFormat.format(cal.getTime())); // 昨天

        cal.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.MONDAY);
        System.out.println("星期一 = " + simpleDateFormat.format(cal.getTime())); // 本周1

        cal.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.SUNDAY);
        System.out.println("星期天 = " + simpleDateFormat.format(cal.getTime()));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        System.out.println("本月第一天为 = " + simpleDateFormat.format(calendar.getTime())); // 本月1日

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("本月最后一天为 = " + simpleDateFormat.format(calendar.getTime()));
        return null;
    }

    private static String UPDATED_AT = "%1$s前";

    private static String UPDATED_JUST_NOW = "刚刚更新";

    /**
     * 一分钟的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MINUTE = 60 * 1000;

    /**
     * 一小时的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_HOUR = 60 * ONE_MINUTE;

    /**
     * 一天的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_DAY = 24 * ONE_HOUR;

    /**
     * 一月的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MONTH = 30 * ONE_DAY;

    /**
     * 一年的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_YEAR = 12 * ONE_MONTH;

    /**
     * 日期与当前日期的差值
     * 小于1分数的  返回 刚刚更新
     * 小于1小的    返回  *分钟前
     * 小于1天的    返回  *小时前
     * 小于1个月的   返回 *天前
     * 大于1个月的  返回 MM-DD
     *
     * @param lastUpdateTime
     */
    public static String refreshUpdatedAtValue(long lastUpdateTime) {
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastUpdateTime;
        long timeIntoFormat;
        String updateAtValue;
        if (timePassed < ONE_MINUTE) {
            updateAtValue = UPDATED_JUST_NOW;
        } else if (timePassed < ONE_HOUR) {
            timeIntoFormat = timePassed / ONE_MINUTE;
            String value = timeIntoFormat + "分钟";
            updateAtValue = String.format(UPDATED_AT, value);
        } else if (timePassed < ONE_DAY) {
            timeIntoFormat = timePassed / ONE_HOUR;
            String value = timeIntoFormat + "小时";
            updateAtValue = String.format(UPDATED_AT, value);
        } else if (timePassed < ONE_MONTH) {
            timeIntoFormat = timePassed / ONE_DAY;
            String value = timeIntoFormat + "天";
            updateAtValue = String.format(UPDATED_AT, value);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            updateAtValue = sdf.format(new Date(lastUpdateTime));
        }
        return updateAtValue;
    }

    /**
     * 日期与当前日期的差值
     * 大于1天的    返回  *天
     * 大于1小时    返回  *小时*分钟
     * 小于1小时    返回  *分钟
     *
     * @param lastUpdateTime
     */
    public static String refreshUpdatedAtValue2(long lastUpdateTime) {
        long currentTime = System.currentTimeMillis();
        long timePassed = Math.abs(currentTime - lastUpdateTime);
        long timeIntoFormat;
        String updateAtValue;
        if (timePassed > ONE_DAY) {
            timeIntoFormat = timePassed / ONE_DAY;
            updateAtValue = timeIntoFormat + "天";
        } else if (timePassed > ONE_HOUR) {
            timeIntoFormat = timePassed / ONE_HOUR;
            long minute = (timePassed % ONE_HOUR) / ONE_MINUTE;
            updateAtValue = timeIntoFormat + "小时" + minute + "分钟";
        } else {
            updateAtValue = timePassed / ONE_MINUTE + "分钟";
//            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
//            updateAtValue = sdf.format(new Date(lastUpdateTime));
        }
        return updateAtValue;
    }

    /**
     * 转时间戳
     *
     * @param strTime
     * @return
     * @throws java.text.ParseException
     */
    public static long formatToTimes(String strTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getNowWeekBegin() {
        int mondayPlus;
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            mondayPlus = 0;
        } else {
            mondayPlus = 1 - dayOfWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();


        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);

        return preMonday + " 00:00:00";


    }

    /**
     * 比较time大小
     * time1 大于 time2时返回 1
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int compareTime(Time time1, Time time2) {
        int flag = 0;
        int intTime1 = Integer.parseInt(getDateFormat(time1, "HHmm"));
        int intTime2 = Integer.parseInt(getDateFormat(time2, "HHmm"));
        if (intTime1 > intTime2) {
            flag = 1;
        } else if (intTime1 < intTime2) {
            flag = -1;
        }
        return flag;
    }

    public static void main(String[] arg) throws Exception {
//        System.out.println(getNowWeekBegin());
//        long time1 = System.currentTimeMillis() - 50;
//        System.out.println("UPDATED_JUST_NOW:" + refreshUpdatedAtValue(time1));

//        long time2 = System.currentTimeMillis() - 5*ONE_MINUTE;
//        System.out.println(refreshUpdatedAtValue2(time2));
//
//        long time32 = System.currentTimeMillis() - 5*ONE_HOUR-10*ONE_MINUTE;
//        System.out.println(refreshUpdatedAtValue2(time32));
//
//        long time3 = System.currentTimeMillis() - 5*ONE_DAY-5*ONE_HOUR;
//        System.out.println(refreshUpdatedAtValue2(time3));
//
//        long time4 = System.currentTimeMillis() - 5*ONE_MONTH;
//        System.out.println(refreshUpdatedAtValue2(time4));


        Date date2 = getDate("2015-10-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("10月" + " " + date2.getTime());

        Date date3 = getDate("2015-11-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("11月" + " " + date3.getTime());

        Date date4 = getDate("2015-12-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("12月" + " " + date4.getTime());

        Date date5 = getDate("2016-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("16年1月" + " " + date5.getTime());

        Date date6 = getDate("2016-02-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("16年2月" + " " + date6.getTime());

        Date date7 = getDate("2016-03-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("16年3月" + " " + date7.getTime());

        Date date8 = getDate("2016-04-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("16年4月" + " " + date8.getTime());

        Date date9 = getDate("2016-05-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("16年5月" + " " + date9.getTime());

        Date date10 = getDate("2016-06-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("16年6月" + " " + date10.getTime());

        Date date11 = getDate("2016-07-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("16年7月" + " " + date11.getTime());

        Date date12 = getDate("2016-08-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("16年8月" + " " + date12.getTime());

//        System.out.println((date.getTime()-1441036800000l)/1000/60/60/24);
//        System.out.println(refreshUpdatedAtValue2(formatToTimes("2015-06-18 01:08:00")));
//
//        System.out.println(formatToTimes("2015-06-18 01:08:00"));
//        Date d1 = new Date(System.currentTimeMillis()- 2*24*60 * 60 * 1000);
//        Date d2 = new Date(System.currentTimeMillis());
//        System.out.println(compareDate("2015-01-01", d1, d2));
//        System.out.println(compareDate("2015-04-14", d1, d2));
//        System.out.println(compareDate("2015-04-15", d1, d2));
//        System.out.println(compareDate("2015-04-16", d1, d2));
//
//        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
//        String time="2015-01-23 22:57:45";
//
//        Date date = format.parse(time);
//        System.out.println(date.getTime());
    }
}

