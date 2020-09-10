package indi.xk.report.pojo;

import indi.xk.report.pojo.validate.Validate4Add;
import indi.xk.report.pojo.validate.Validate4Update;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author xk
 * @Date 2020/8/31 19:18
 * @Version 1.0
 */
@Data
@ToString
public class Vote {
    @NotNull(message = "请重新登录",groups = Validate4Update.class)
    private Integer id;
    @NotNull(message = "请重新登录",groups = {Validate4Add.class,Validate4Update.class})
    private Integer userId;
    @NotBlank(message = "主题不能为空",groups = Validate4Add.class)
    private String title;
    @NotNull(message = "选项类型不能为空",groups = Validate4Add.class)
    private Integer type;
    private Integer limit;
    @NotNull(message = "截止日期不能为空",groups = Validate4Add.class)
    private String deadline;
    private Integer number;
    @NotNull(message = "投票总人数不能为空",groups = Validate4Add.class)
    private Integer amount;
}
