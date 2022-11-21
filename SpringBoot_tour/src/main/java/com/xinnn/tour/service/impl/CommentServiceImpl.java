package com.xinnn.tour.service.impl;

import com.github.pagehelper.PageHelper;
import com.xinnn.tour.mapper.CommentMapper;
import com.xinnn.tour.pojo.Comment;
import com.xinnn.tour.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Comment Service接口实现类
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentListByScenicId(Integer scenicId, Integer page) {
        //使用pagehelper对评论列表进行分页 每页固定3条
        PageHelper.startPage(page, 3);
        return commentMapper.getCommentListByScenicId(scenicId);
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.addComment(comment);
    }

    @Override
    public Integer getCommentSumPage(Integer scenicId) {
        Integer sumNum = commentMapper.getCommentSumNum(scenicId);
        return (sumNum + 3) / 3;
    }
}
