package indi.xk.report.service;

import indi.xk.report.pojo.Blogger;

/**
 * @Author xk
 * @Date 2020/8/20 18:42
 * @Version 1.0
 */
public interface BloggerService {
    /**
     * 登录
     * @param blogger
     */
    void login(Blogger blogger);

    /**
     * 注册
     * @param blogger
     */
    void register(Blogger blogger);
}
