package com.xinnn.tour.controller;

import com.xinnn.tour.pojo.Comment;
import com.xinnn.tour.pojo.User;
import com.xinnn.tour.service.CommentService;
import com.xinnn.tour.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    //添加评论
    @RequestMapping("/tour/addcomment")
    public Map<String, Object> addComment(@RequestParam("scenicId")Integer scenicId,@RequestParam("content")String content, HttpSession session){
        LocalDateTime dateTime = LocalDateTime.now();
        User user = (User) session.getAttribute("currUser");
        //判断user是否为空 为空则代表为登录 无法评论
        if(user != null){

            //创建一个实体对象
            Comment comment = new Comment(null,content,dateTime,user,scenicId);
            //添加评论
            commentService.addComment(comment);
            return ResultMap.resultJson(100,"评论成功");
        }else{
            return ResultMap.resultJson(102,"游客");
        }
    }
    //根据景点id和页码向前端返回评论列表
    @RequestMapping("/tour/comments")
    public Map<String, Object> getCommentList(@RequestParam("scenicId")Integer scenicId,@RequestParam("page")Integer page){
        //获取当前页的详情
        Map<String, Object> map = commentService.getCommentListByScenicId(scenicId, page);
        return ResultMap.resultJson(100, map);
    }
}
