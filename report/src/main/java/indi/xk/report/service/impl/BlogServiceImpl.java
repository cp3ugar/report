package indi.xk.report.service.impl;

import indi.xk.report.mapper.BlogMapper;
import indi.xk.report.pojo.Blog;
import indi.xk.report.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xk
 * @Date 2020/8/20 18:45
 * @Version 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void addBlog(Blog blog) {
        blogMapper.addBlog(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        checkAuth(blog.getId());
        blogMapper.updateBlog(blog);
    }

    @Override
    public void deleteBlog(Integer id) {
        checkAuth(id);
        blogMapper.deleteBlog(id);
    }

    @Override
    public Blog detailBlog(Integer id) {
        return blogMapper.detailBlog(id);
    }

    private void checkAuth(Integer id){
        Blog exit = blogMapper.detailBlog(id);
        if(!exit.getUserId().equals(id)){
            throw new RuntimeException("本人才能操作");
        }
    }
}
