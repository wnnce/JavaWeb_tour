package com.xinnn.tour.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * 评论实体类
 */
public class Comment {
    private Integer id;
    private String content;
    //Json返回时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentDate;
    private User user;
    private Integer scenicId;
    public Comment(){

    }
    public Comment(Integer id, String content, LocalDateTime commentDate, User user, Integer scenicId) {
        this.id = id;
        this.content = content;
        this.commentDate = commentDate;
        this.user = user;
        this.scenicId = scenicId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }
}
