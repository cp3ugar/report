package indi.xk.blog.service.impl;

import indi.xk.blog.bean.User;
import indi.xk.blog.mapper.UserMapper;
import indi.xk.blog.service.UserService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xk
 * @Date 2020/8/20 18:44
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void login(User user) {
        int row = userMapper.login(user);
        if(row != 1){
            throw new RuntimeException("账号密码不正确");
        }
    }

    @Override
    public void register(User user) {
        user.setPassword(MD5Encoder.encode(user.getPassword().getBytes()));
        userMapper.register(user);
    }
}
