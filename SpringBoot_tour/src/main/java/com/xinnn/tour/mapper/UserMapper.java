package com.xinnn.tour.mapper;

import com.xinnn.tour.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    /**
     * 验证登录
     * @param username
     * @param passwd
     * @return
     */
    User login(@Param("username") String username, @Param("passwd") String passwd);

    /**
     * 注册 返回自增的主键id给user
     * @param user
     */
    void sign(User user);

    /**
     * 通过id获取用户
     * @param id
     * @return
     */
    User getUserById(@Param("id")Integer id);
}
