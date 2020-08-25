package indi.xk.report.pojo;

import lombok.Data;

/**
 * @Author xk
 * @Date 2020/8/20 21:04
 * @Version 1.0
 */
@Data
public class Blogger extends BaseEntity{
    private Integer id;
    private String account;
    private String password;
}
