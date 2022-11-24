package com.xinnn.tour.service;

import com.xinnn.tour.pojo.Comment;

import java.util.Map;

/**
 * Comment Service接口
 */
public interface CommentService {
    //根据景点id获取评论列表
    Map<String, Object> getCommentListByScenicId(Integer scenicId, Integer page);
    //添加评论
    void addComment(Comment comment);
}
