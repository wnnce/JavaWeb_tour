package com.xinnn.tour.util;

/**
 * 字符串工具类
 */
public class StringUtil {

    //字符串截取
    public static String cutString(String value){
        if(value.length() > 60){
            value = value.substring(0, 60) + "...";
        }
        return value;
    }
}
