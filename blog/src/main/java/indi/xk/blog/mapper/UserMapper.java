package indi.xk.blog.mapper;

import indi.xk.blog.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @Author xk
 * @Date 2020/8/20 18:45
 * @Version 1.0
 */
@Repository
public interface UserMapper {
    /**
     * 登录
     * @param user
     * @return
     */
    int login(User user);

    /**
     * 注册
     * @param user
     */
    void register(User user);
}
