package indi.xk.report.mapper;

import indi.xk.report.pojo.Blogger;
import indi.xk.report.pojo.User;
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
    User login(Blogger blogger);

    /**
     * 注册
     * @param blogger
     */
    void register(Blogger blogger);

    /**
     * 通过 account 查找是否存在
     * @param account
     * @return
     */
    int findAccount(String account);
}
