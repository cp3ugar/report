package indi.xk.blog.service;

import indi.xk.blog.bean.Blog;

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
     * @param id
     */
    void deleteBlog(Integer id);

    /**
     * 详情
     * @param id
     * @return
     */
    Blog detailBlog(Integer id);
}
