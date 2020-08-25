package indi.xk.report.service.impl;

import indi.xk.report.mapper.BloggerMapper;
import indi.xk.report.pojo.Blogger;
import indi.xk.report.service.BloggerService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xk
 * @Date 2020/8/20 18:44
 * @Version 1.0
 */
@Service
public class BloggerServiceImpl implements BloggerService {
    @Autowired
    private BloggerMapper bloggerMapper;

    @Override
    public void login(Blogger blogger) {
        int row = bloggerMapper.login(blogger);
        if(row != 1){
            throw new RuntimeException("账号密码不正确");
        }
    }

    @Override
    public void register(Blogger blogger) {
        blogger.setPassword(MD5Encoder.encode(blogger.getPassword().getBytes()));
        bloggerMapper.register(blogger);
    }
}
