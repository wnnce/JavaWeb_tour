package com.xinnn.tour.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinnn.tour.mapper.ScenicMapper;
import com.xinnn.tour.pojo.Scenic;
import com.xinnn.tour.service.ScenicService;
import com.xinnn.tour.util.StringUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Scenic Service接口实现类
 */
@Service
public class ScenicServiceImpl implements ScenicService {
    @Autowired
    private ScenicMapper scenicMapper;
    @Autowired
    private SolrClient solrClient;

    @Override
    public Map<String, Object> getScenicListByPage(Integer page) {
        //使用pagehelper插件自动获取分页 固定每页5条数据
        Page<Object> pageObj = PageHelper.startPage(page, 5);
        List<Scenic> scenicList =  scenicMapper.getScenicListByPage();
        for(Scenic scenic : scenicList){
            //截取景点简介 景点列表中只显示一部分简介
            scenic.setContent(StringUtil.cutString(scenic.getContent()));
        }
        Map<String, Object> map = new HashMap<>();
        //添加当前页
        map.put("currPage", pageObj.getPageNum());
        //添加总页数
        map.put("sumPage", pageObj.getPages());
        map.put("scenicList", scenicList);
        return map;
    }

    @Override
    public Scenic getScenicById(Integer id) {
        //调用添加景点点击数方法
        addScenicClickNumById(id);
        return scenicMapper.getScenicById(id);
    }

    @Override
    public List<Scenic> getScenicTopList() {
        return scenicMapper.getScenicTop();
    }

    @Override
    public void addScenicClickNumById(Integer id) {
        scenicMapper.addScenicClickNumById(id);
    }


    /**
     * 通过关键字和页码返回从solr查询的数据
     * @param keyWord
     * @param page
     * @return
     */
    @Override
    public Map<String, Object> searchScenicBySolr(String keyWord, Integer page) {
        //装载当前页码 总页数 和查询数据 返回给前端
        Map<String, Object> map = new HashMap<>();
        List<Scenic> scenicList = new ArrayList<>();
        SolrQuery query = new SolrQuery();
        //只查询solr数据源的标题
        query.setQuery("title:" + keyWord);
        //通过id升序排序
        query.setSort("id", SolrQuery.ORDER.asc);
        //把page转换为solr的开始条数
        query.setStart((page -1) * 5);
        map.put("currPage", page);
        //一次查询返回5条数据
        query.setRows(5);
        try{
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documents = response.getResults();
            //获取总记录条数 并转换为页数并添加到map集合
            long sumNum = documents.getNumFound();
            int sumPage = (int) ((sumNum + 5) / 5);
            map.put("sumPage", sumPage);
            for(SolrDocument document : documents){
                //获取各个字段的值
                Integer id = Integer.parseInt((String) document.getFieldValue("id"));
                String title = (String) document.getFieldValue("title");
                String content = (String) document.getFieldValue("content");
                String scenicDateStr = (String) document.getFieldValue("scenic_date");
                String imgUrl = (String) document.getFieldValue("img_url");
                //截取景点简介
                content = StringUtil.cutString(content);
                //截取时间 去除毫秒
                scenicDateStr = scenicDateStr.substring(0, scenicDateStr.lastIndexOf("."));
                //将时间格式化为LocalDateTime类型
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime scenicDate = LocalDateTime.parse(scenicDateStr, formatter);
                scenicList.add(new Scenic(id, title, content, scenicDate, imgUrl, null));
            }
        }catch (IOException | SolrServerException e){
            e.printStackTrace();
            throw new RuntimeException("solr error");
        }
        map.put("scenicList", scenicList);
        return map;
    }
}
