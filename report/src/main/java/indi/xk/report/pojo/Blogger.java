package indi.xk.report.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author xk
 * @Date 2020/8/20 21:04
 * @Version 1.0
 */
@Data
public class Blogger extends BaseEntity{
    private Integer id;
    @NotBlank(message = "昵称不能为空")
    private String name;
    @NotBlank(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "验证码不能为空")
    private String code;
}
