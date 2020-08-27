package indi.xk.report.service;

import indi.xk.report.pojo.Blogger;
import indi.xk.report.pojo.User;

/**
 * @Author xk
 * @Date 2020/8/20 18:42
 * @Version 1.0
 */
public interface BloggerService {
    /**
     * 登录
     * @param blogger
     * @return User
     */
    User login(Blogger blogger);

    /**
     * 注册
     * @param blogger
     */
    void register(Blogger blogger);
}
