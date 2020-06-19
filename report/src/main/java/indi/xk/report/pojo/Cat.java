package indi.xk.report.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @Author xk
 * @Date 2020/6/19 17:45
 * @Version 1.0
 */
@Data
@Builder
public class Cat {
    private Integer id;
    private String name;
    private Integer type;
    private String nickName;
}
