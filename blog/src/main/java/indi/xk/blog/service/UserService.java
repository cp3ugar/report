package indi.xk.blog.service;

import indi.xk.blog.bean.User;

/**
 * @Author xk
 * @Date 2020/8/20 18:42
 * @Version 1.0
 */
public interface UserService {
    /**
     * 登录
     * @param user
     */
    void login(User user);

    /**
     * 注册
     * @param user
     */
    void register(User user);
}
