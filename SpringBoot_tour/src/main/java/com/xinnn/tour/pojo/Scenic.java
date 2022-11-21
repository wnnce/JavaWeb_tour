package com.xinnn.tour.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 景点实体类
 */
public class Scenic {
    private Integer id;
    private String title;
    private String content;
    //Json返回时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime scenicDate;
    private String imgUrl;
    private Integer clickNum;


    public Scenic(){

    }
    public Scenic(Integer id, String title, String content, LocalDateTime scenicDate, String imgUrl, Integer clickNum) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.scenicDate = scenicDate;
        this.imgUrl = imgUrl;
        this.clickNum = clickNum;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getScenicDate() {
        return scenicDate;
    }

    public void setScenicDate(LocalDateTime scenicDate) {
        this.scenicDate = scenicDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }
}
