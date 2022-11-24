package com.xinnn.tour.service;

import com.xinnn.tour.pojo.Scenic;

import java.util.List;
import java.util.Map;

/**
 * Scenic Service接口
 */
public interface ScenicService {
    //根据页码获取景点列表
    Map<String, Object> getScenicListByPage(Integer page);
    //根据id获取景点详情
    Scenic getScenicById(Integer id);
    //获取景点点击排行榜
    List<Scenic> getScenicTopList();
    //增加景点点击量
    void addScenicClickNumById(Integer id);
    Map<String, Object> searchScenicBySolr(String keyWord, Integer page);
}
