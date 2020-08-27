package indi.xk.report.service;

import indi.xk.report.pojo.Blog;
import indi.xk.report.utils.PageView;

/**
 * @Author xk
 * @Date 2020/8/20 18:43
 * @Version 1.0
 */
public interface BlogService {
    /**
     * 添加
     * @param blog
     */
    void addBlog(Blog blog);

    /**
     * 编辑
     * @param blog
     */
    void updateBlog(Blog blog);

    /**
     * 删除
     * @param blog
     */
    void deleteBlog(Blog blog);

    /**
     * 详情
     * @param id
     * @return
     */
    Blog detailBlog(Integer id);

    /**
     * 列表
     * @param pageView
     * @return
     */
    PageView<Blog> listBlog(PageView pageView);
}
