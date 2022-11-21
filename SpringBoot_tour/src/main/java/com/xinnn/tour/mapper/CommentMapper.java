package com.xinnn.tour.mapper;

import com.xinnn.tour.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 通过景点id获取所有评论
     * @param scenicId
     * @return
     */
    List<Comment> getCommentListByScenicId(@Param("scenicId")Integer scenicId);

    /**
     * 添加评论
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 通过景点id获取指定景点的评论总条数
     * @return
     */
    Integer getCommentSumNum(@Param("scenicId")Integer scenicId);
}
