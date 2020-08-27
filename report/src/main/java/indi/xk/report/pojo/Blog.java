package indi.xk.report.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author xk
 * @Date 2020/8/20 19:04
 * @Version 1.0
 */
@Data
public class Blog extends BaseEntity{
    private Integer id;
    private Integer userId;
    private String content;
    private String userName;
    private List<Comment> comments;
}
