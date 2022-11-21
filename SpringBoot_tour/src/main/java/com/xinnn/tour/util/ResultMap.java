package com.xinnn.tour.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 工具类
 * 规范Json的返回格式
 */
public class ResultMap {
    //返回状态码、消息和数据
    public static Map<String, Object> resultJson(Integer code, String msg, Object data){
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }
    //返回状态码 消息
    public static Map<String, Object> resultJson(Integer code, String msg){
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }
    //返回状态码 数据
    public static Map<String, Object> resultJson(Integer code, Object data){
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("data", data);
        return map;
    }
}
