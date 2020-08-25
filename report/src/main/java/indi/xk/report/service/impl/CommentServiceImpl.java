package indi.xk.report.service.impl;

import indi.xk.report.mapper.CommentMapper;
import indi.xk.report.pojo.Comment;
import indi.xk.report.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xk
 * @Date 2020/8/20 18:45
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void commentBlog(Comment comment) {
        commentMapper.add(comment);
    }
}
