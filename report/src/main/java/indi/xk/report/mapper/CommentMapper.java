package indi.xk.report.mapper;

import indi.xk.report.pojo.Comment;
import org.springframework.stereotype.Repository;

/**
 * @Author xk
 * @Date 2020/8/20 18:46
 * @Version 1.0
 */
@Repository
public interface CommentMapper {
    /**
     * 添加
     * @param comment
     */
    void add(Comment comment);
}
