package com.xinnn.tour.controller;

import com.xinnn.tour.pojo.User;
import com.xinnn.tour.service.UserService;
import com.xinnn.tour.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    //登录方法
    @RequestMapping("/tour/login")
    public Map<String,Object> login(@RequestParam("username")String username, @RequestParam("passwd")String passwd, HttpSession session){
        User user = userService.login(username, passwd);
        //判断service层返回的user对象是否为空 不为空则将其保存到session中
        if(user != null){
            session.setAttribute("currUser", user);
            //返回状态码 让前端判断是否跳转页面
            return ResultMap.resultJson(100,"登录成功");
        }else{
            return ResultMap.resultJson(1002,"用户名或密码错误");
        }
    }
    //注册方法
    @RequestMapping("/tour/sign")
    public Map<String,Object> sign(@RequestParam("username")String username, @RequestParam("passwd")String passwd,HttpSession session){
        //创建一个user对象
        User user = new User(null,username,passwd,null);
        userService.sign(user);
        //注册成功user对象的id会被自动赋值 如果为空则说明注册失败 不为空则将其保存到session中
        if(user.getId() != null){
            session.setAttribute("currUser",user);
            return ResultMap.resultJson(100,"注册成功");
        }else{
            return ResultMap.resultJson(500,"系统异常");
        }
    }
    //获取user对象 携带Cookie的情况下 拦截器会将用户信息保存到session里面 客户端无需登录也能看到用户信息
    @RequestMapping("/tour/user")
    public Map<String, Object> user(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        //从session中取出user对象 如果为空则说明未登录 不为空就将密码消除并返回给客户端
        if(user != null){
            user.setPasswd("");
            return ResultMap.resultJson(100,"登录用户",user);
        }else{
            return ResultMap.resultJson(102,"游客");
        }
    }
    //退出登录
    @RequestMapping("/tour/signout")
    public Map<String, Object> signOut(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        //user对象不为空就从session中删除
        if(user != null){
            session.removeAttribute("currUser");
            return ResultMap.resultJson(100,"退出登录成功");
        }else{
            return ResultMap.resultJson(102,"未登录，无法退出");
        }
    }
}
