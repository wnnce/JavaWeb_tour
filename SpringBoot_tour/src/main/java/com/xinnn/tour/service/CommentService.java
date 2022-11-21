package com.xinnn.tour.service;

import com.xinnn.tour.pojo.Comment;

import java.util.List;

/**
 * Comment Service接口
 */
public interface CommentService {
    //根据景点id获取评论列表
    List<Comment> getCommentListByScenicId(Integer scenicId, Integer page);
    //添加评论
    void addComment(Comment comment);
    //获取当前景点评论的总条数
    Integer getCommentSumPage(Integer scenicId);
}
