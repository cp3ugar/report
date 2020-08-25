package indi.xk.report.mapper;

import indi.xk.report.pojo.Blogger;
import org.springframework.stereotype.Repository;

/**
 * @Author xk
 * @Date 2020/8/20 18:45
 * @Version 1.0
 */
@Repository
public interface BloggerMapper {
    /**
     * 登录
     * @param blogger
     * @return
     */
    int login(Blogger blogger);

    /**
     * 注册
     * @param blogger
     */
    void register(Blogger blogger);
}
