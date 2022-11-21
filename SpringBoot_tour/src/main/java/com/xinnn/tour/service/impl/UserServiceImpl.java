package com.xinnn.tour.service.impl;

import com.xinnn.tour.mapper.UserMapper;
import com.xinnn.tour.pojo.User;
import com.xinnn.tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service接口实现类
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String passwd) {
        return userMapper.login(username, passwd);
    }

    @Override
    public void sign(User user) {
        userMapper.sign(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
