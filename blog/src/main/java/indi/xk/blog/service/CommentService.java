package indi.xk.blog.service;

import indi.xk.blog.bean.Comment;

/**
 * @Author xk
 * @Date 2020/8/20 18:43
 * @Version 1.0
 */
public interface CommentService {
    /**
     * 评论
     * @param comment
     */
    void commentBlog(Comment comment);
}
