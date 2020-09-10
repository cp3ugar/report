package indi.xk.report.pojo;

import lombok.Data;

/**
 * @Author xk
 * @Date 2020/9/8 12:28
 * @Version 1.0
 */
@Data
public class VoteRecord extends BaseEntity{
    private Integer id;
    private Integer userId;
    private Integer voteId;
    private Integer optionId;
}
