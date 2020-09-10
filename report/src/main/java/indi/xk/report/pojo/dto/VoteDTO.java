package indi.xk.report.pojo.dto;

import indi.xk.report.pojo.Option;
import indi.xk.report.pojo.Vote;
import indi.xk.report.pojo.validate.Validate4Add;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

/**
 * @Author xk
 * @Date 2020/8/31 19:51
 * @Version 1.0
 */
@Data
@ToString
public class VoteDTO extends Vote {
    @Size(min = 3,message = "请添加至少三个选项",groups = Validate4Add.class)
    private List<Option> options;
    private Map voteData;
    private String optionId;
    private Integer voteUserId;
}
