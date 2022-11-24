package com.xinnn.tour.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinnn.tour.mapper.CommentMapper;
import com.xinnn.tour.pojo.Comment;
import com.xinnn.tour.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Comment Service接口实现类
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Map<String, Object> getCommentListByScenicId(Integer scenicId, Integer page) {
        //使用pagehelper对评论列表进行分页 每页固定3条
        Page<Object> pageObj = PageHelper.startPage(page, 3);
        //获取评论列表
        List<Comment> commentList = commentMapper.getCommentListByScenicId(scenicId);
        Map<String, Object> map = new HashMap<>();
        //添加当前页
        map.put("currPage", pageObj.getPageNum());
        //添加总页数
        map.put("sumPage", pageObj.getPages());
        //添加评论列表
        map.put("commentList", commentList);
        return map;
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.addComment(comment);
    }

}
