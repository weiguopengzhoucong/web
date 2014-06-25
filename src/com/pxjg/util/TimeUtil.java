package com.pxjg.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @项目名：houseInfo
 * @包名：net.hlj.common.util
 * @文件名：TimeUtil.java
 * @日期：Jan 31, 2012 2:43:35 PM
 * @备注:时间工具
 * @作者：apple
 */
public class TimeUtil {
	
    private final static SimpleDateFormat longDateFormatter = new
            SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat longDate1Formatter = new
    SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat timeDateFormatter = new
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @SuppressWarnings("unused")
	private final static SimpleDateFormat shortTimeDateFormatter = new
            SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    
    public static Date string2Time(String value) {
        if (value == null) {
            return null;
        }
        try {
            return timeDateFormatter.parse(value);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 根据特定时间格式获得时间字符串
     * yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String time1String(Date date) {
        if (date == null) {
            return null;
        }
        return timeDateFormatter.format(date);
    }
    /**
     * 根据特定时间格式获得时间字符串
     * yyyy-MM-dd
     * @param date
     * @return
     */
    public static String time2String(Date date) {
        if (date == null) {
            return null;
        }
        return longDateFormatter.format(date);
    }
    /**
     * 根据特定时间格式获得时间字符串
     * shortTimeDateFormatter
     * @param date
     * @return
     */
    public static String time3String(Date date) {
        if (date == null) {
            return null;
        }
        return longDateFormatter.format(date);
    }
    /**
     * 根据特定时间格式获得时间字符串
     * shortTimeDateFormatter
     * @param date
     * @return
     */
    public static String time4String(Date date) {
        if (date == null) {
            return null;
        }
        return longDate1Formatter.format(date);
    }
    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime() {
        return time1String(new Date());
    }
    
    public static void main(String[] args) {
		System.out.println(time1String(new Date()));
	}
}
