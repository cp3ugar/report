package indi.xk.blog.bean;

import lombok.Data;

/**
 * @Author xk
 * @Date 2020/8/20 19:06
 * @Version 1.0
 */
@Data
public class Comment {
    private Integer id;
    private Integer blogId;
    private Integer userId;
    private String content;
}
