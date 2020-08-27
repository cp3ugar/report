package indi.xk.report.controller;

import indi.xk.report.pojo.Blog;
import indi.xk.report.service.BlogService;
import indi.xk.report.utils.PageView;
import indi.xk.report.utils.ReturnObject;
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
    private ReturnObject addBlog(Blog blog){
        if(blog.getUserId() == null){
            return ReturnObject.outError("缺少用户id");
        }
        blogService.addBlog(blog);
        return ReturnObject.outSuccess("发表成功");
    }

    @PostMapping("/updateBlog")
    private ReturnObject updateBlog(Blog blog){
        if(blog.getUserId() == null){
            return ReturnObject.outError("缺少用户id");
        }
        blogService.updateBlog(blog);
        return ReturnObject.outSuccess("编辑成功");
    }

    @PostMapping("/deleteBlog")
    private ReturnObject deleteBlog(Blog blog){
        if(blog.getUserId() == null){
            return ReturnObject.outError("缺少用户id");
        }
        blogService.deleteBlog(blog);
        return ReturnObject.outSuccess("删除成功");
    }

    @GetMapping("/blogs/{id}")
    private ReturnObject detailBlog(@PathVariable Integer id){
        ReturnObject ro = new ReturnObject();
        ro.setData(blogService.detailBlog(id));
        ro.setCode(0);
        return ro;
    }

    @GetMapping("/blogs")
    @ResponseBody
    private ReturnObject listBlog(@RequestParam(value = "page",defaultValue = "1")Integer pageNow,
            @RequestParam(value = "limit",defaultValue = "10")Integer pageSize){
        PageView pageView = new PageView(pageSize,pageNow);
        PageView<Blog> blogs = blogService.listBlog(pageView);
        return new ReturnObject(0,"",blogs.getRowCount(),blogs.getRecords());
    }
}
