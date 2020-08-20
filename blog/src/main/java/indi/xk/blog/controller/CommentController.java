package indi.xk.blog.controller;

import indi.xk.blog.bean.Comment;
import indi.xk.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xk
 * @Date 2020/8/20 17:55
 * @Version 1.0
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comments")
    private String commentBlog(Comment comment){
        commentService.commentBlog(comment);
        return "评论成功";
    }
}
