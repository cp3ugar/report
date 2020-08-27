package indi.xk.report.service.impl;

import indi.xk.report.mapper.BloggerMapper;
import indi.xk.report.pojo.Blogger;
import indi.xk.report.pojo.User;
import indi.xk.report.service.BloggerService;
import indi.xk.report.utils.BaseRuntimeException;
import indi.xk.report.utils.MD5;
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
    public User login(Blogger blogger) {
        blogger.setPassword(MD5.getMD5(blogger.getPassword()));
        return bloggerMapper.login(blogger);
    }

    @Override
    public void register(Blogger blogger) {
        int row = bloggerMapper.findAccount(blogger.getAccount());
        if(row > 0){
            throw new BaseRuntimeException(500,"该账号已经被注册");
        }
        blogger.setPassword(MD5.getMD5(blogger.getPassword()));
        bloggerMapper.register(blogger);
    }
}
