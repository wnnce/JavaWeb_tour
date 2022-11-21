package com.xinnn.tour.controller;

import com.xinnn.tour.pojo.Scenic;
import com.xinnn.tour.service.ScenicService;
import com.xinnn.tour.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ScenicController {

    @Autowired
    private ScenicService scenicService;

    //根据前端请求的页码返回景点列表
    @RequestMapping("/tour/item")
    public Map<String, Object> tourItem(@RequestParam("page")Integer page){
        List<Scenic> scenicList = scenicService.getScenicListByPage(page);
        Integer sumPage = scenicService.getScenicSumPage();
        //向map集合中添加当前页码和总页码 并返回给前端 方便前端做翻页判断
        Map<String, Object> map = new HashMap<>();
        map.put("currPage", page);
        map.put("sumPage", sumPage);
        map.put("scenicList", scenicList);
        return ResultMap.resultJson(100,map);
    }
    //返回景点点击排行榜
    @RequestMapping("/tour/top")
    public Map<String, Object> tourTop(){
        List<Scenic> scenicTop = scenicService.getScenicTopList();
        return ResultMap.resultJson(100,scenicTop);
    }
    //根据前端请求的景点id返回景点详情
    @RequestMapping("/tour/info")
    public Map<String, Object> scenicInfo(@RequestParam("id")Integer id){
        Scenic scenic = scenicService.getScenicById(id);
        return ResultMap.resultJson(100, scenic);
    }
    @RequestMapping("/tour/search")
    public Map<String, Object> search(@RequestParam("keyWord")String keyWord, @RequestParam("page") Integer page){
        Map<String, Object> map = scenicService.searchScenicBySolr(keyWord, page);
        return ResultMap.resultJson(100, map);
    }
}
