package indi.xk.report.pojo;

import lombok.Data;

/**
 * @Author xk
 * @Date 2020/8/20 19:06
 * @Version 1.0
 */
@Data
public class Comment extends BaseEntity{
    private Integer id;
    private Integer blogId;
    private Integer userId;
    private String content;
}
