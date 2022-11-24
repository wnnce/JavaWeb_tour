package com.xinnn.tour.mapper;

import com.xinnn.tour.pojo.Scenic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScenicMapper {
    /**
     * 通过页码获取景点列表
     * @return
     */
    List<Scenic> getScenicListByPage();

    /**
     * 通过id获取景点详情
     * @param id
     * @return
     */
    Scenic getScenicById(@Param("id")Integer id);

    /**
     * 获取点击排行榜
     * @return
     */
    List<Scenic> getScenicTop();

    /**
     * 增加景点点击数
     * @param id
     */
    void addScenicClickNumById(@Param("id")Integer id);

}
