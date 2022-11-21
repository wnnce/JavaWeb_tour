package com.xinnn.tour.service;

import com.xinnn.tour.pojo.User;

/**
 * User Service接口
 */
public interface UserService {
    //登录方法 登录成功返回一个User对象
    User login(String username, String passwd);
    //注册方法 注册成功后为传入的user对象id属性赋值
    void sign(User user);
    //通过id获取user对象
    User getUserById(Integer id);
}
