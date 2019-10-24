package com.jinqu.standardNew.util;

import com.ab.util.AbDateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.rong.app.utils.DateUtils.dateToString;
import static io.rong.app.utils.DateUtils.stringToDate;

/**
 * Created by JinQu on 2016/12/16.
 * 时间相关工具类
 */

public class UtilDate {

    /**
     * 获取指定文件大小
     *
     * @param
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(int fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 把时间转换为特定格式的时间戳
     *
     * @param date
     * @return
     */
    public static String formatDateToLong(Date date) {
        try {
            long time = date.getTime();
            return "/Date(" + time + ")/";
        } catch (Exception e) {
            return "";
        }
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws Exception {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    /**
     * 正则匹配时间
     * /Date(1476405410000)/
     *
     * @param date
     * @return
     */
    public static String formatMatchDate(String date, String format) {
        try {
            long time = 0;
            Pattern pattern = Pattern.compile("/Date\\((.*)\\)/");
            Matcher matcher = pattern.matcher(date);
            if (matcher.find()) {
                time = Long.parseLong(matcher.group(1));
                Date d = new Date(time);
                SimpleDateFormat sf = new SimpleDateFormat(format);

                return sf.format(d).equals("1900-01-01") ? "" : sf.format(d);
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public static String changeTime(String date) {
        try {
            return AbDateUtil.getStringByFormat(AbDateUtil.getDateByFormat(date, "yyyy-MM-dd'T'HH:mm:ss"), "yyyy-MM-dd").equals("1900-01-01") ? "" : AbDateUtil.getStringByFormat(AbDateUtil.getDateByFormat(date, "yyyy-MM-dd'T'HH:mm:ss"), "yyyy-MM-dd");
        } catch (Exception e) {
            return date;
        }
    }

    public static String changeTime(String date, String format) {
        try {
            return AbDateUtil.getStringByFormat(AbDateUtil.getDateByFormat(date, "yyyy-MM-dd'T'HH:mm:ss"), "yyyy-MM-dd").equals("1900-01-01") ? "" : AbDateUtil.getStringByFormat(AbDateUtil.getDateByFormat(date, "yyyy-MM-dd'T'HH:mm:ss"), format);
        } catch (Exception e) {
            return date;
        }

    }

    /**
     * 把日期转为字符串
     * "yyyy-MM-dd"
     */
    public static String ConverToString(Date date, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 把字符串转为日期
     *
     * @param strDate
     * @return
     * @throws Exception "yyyy-MM-dd HH:mm:ss"
     */
    public static Date ConverToDate(String strDate, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 两个时间之间毫秒
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getMS(Date date1, Date date2) {
        if (date1 == null)
            return 0;
        if (date2 == null)
            return 0;
        long ms = (date1.getTime() - date2.getTime());
        return ms;
    }

    /**
     * 2个时间间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static String getIntervalTime(Date date1, Date date2) {
        long ms = getMS(date1, date2);
        long min = ms / (1000 * 60);
        if (min < 1) {
            return "刚刚";
        } else if (min < 60) {
            return min + "分钟之前";
        } else if (min < 1440) {
            return (min / 60) + "小时之前";
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(date2);
            return dateString;
        }
    }

    /**
     * 时间转换---下载专用
     *
     * @param dates
     * @return
     */
    public static String FormetDateSizeDownload(long dates) {
        String dateSizeString = "";
        String wrongSize = "0秒";
        if (dates < 0) {
            return "正在计算";
        }
        if (dates == 0) {
            return wrongSize;
        }
        if (dates < 60) {
            dateSizeString = dates + "秒";
        } else if (dates < 3600) {
            dateSizeString = (dates / 60) + "分" + (dates % 60) + "秒";
        } else if (dates < 86400) {
            dateSizeString = (dates / 3600) + "小时" + (dates % 3600 / 60) + "分";
        } else {
            dateSizeString = "1天以上";
        }
        return dateSizeString;
    }


}
