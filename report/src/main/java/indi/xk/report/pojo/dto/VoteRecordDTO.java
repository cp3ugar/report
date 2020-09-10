package indi.xk.report.pojo.dto;

import indi.xk.report.pojo.VoteRecord;
import lombok.Data;

import java.util.Map;

/**
 * @Author xk
 * @Date 2020/9/8 12:30
 * @Version 1.0
 */
@Data
public class VoteRecordDTO extends VoteRecord {
    private Map data;
}
