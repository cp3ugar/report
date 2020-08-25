package indi.xk.report.controller;

import indi.xk.report.pojo.Blog;
import indi.xk.report.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author xk
 * @Date 2020/8/20 18:42
 * @Version 1.0
 */
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/blogs")
    private String addBlog(Blog blog){
        blogService.addBlog(blog);
        return "发表成功";
    }

    @PutMapping("/blogs")
    private String updateBlog(Blog blog){
        blogService.updateBlog(blog);
        return "编辑成功";
    }

    @DeleteMapping("/blogs")
    private String deleteBlog(Integer id){
        blogService.deleteBlog(id);
        return "删除成功";
    }

    @GetMapping("/blogs")
    private Blog detailBlog(Integer id){
        return blogService.detailBlog(id);
    }
}
