package indi.xk.report.service.impl;

import indi.xk.report.mapper.BlogMapper;
import indi.xk.report.mapper.CommentMapper;
import indi.xk.report.pojo.Blog;
import indi.xk.report.pojo.Comment;
import indi.xk.report.service.BlogService;
import indi.xk.report.utils.BaseRuntimeException;
import indi.xk.report.utils.PageView;
import indi.xk.report.utils.PagingUtil;
import indi.xk.report.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xk
 * @Date 2020/8/20 18:45
 * @Version 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addBlog(Blog blog) {
        blogMapper.addBlog(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        checkAuth(blog);
        blogMapper.updateBlog(blog);
    }

    @Override
    public void deleteBlog(Blog blog) {
        checkAuth(blog);
        blogMapper.deleteBlog(blog.getId());
    }

    @Override
    public Blog detailBlog(Integer id) {
        Blog blog = blogMapper.detailBlog(id);
        List<Comment> comments = commentMapper.getComments(id);
        blog.setComments(comments);
        return blog;
    }

    private void checkAuth(Blog blog){
        Blog exit = blogMapper.detailBlog(blog.getId());
        if(!exit.getUserId().equals(blog.getUserId())){
            throw new BaseRuntimeException(500,"本人才能操作");
        }
    }

    @Override
    public PageView<Blog> listBlog(PageView pageView) {
        Map map = new HashMap(1);
        Integer count = blogMapper.count();
        PagingUtil.pagingUtil(count, pageView);
        map.put("pageView", pageView);
        List<Blog> list = blogMapper.listBlog(map);
        if (Utils.isEmpty(pageView)) {
            pageView = new PageView();
        }
        pageView.setRecords(list);
        return pageView;
    }
}
