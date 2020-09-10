package indi.xk.report.mapper;

import indi.xk.report.pojo.Option;
import indi.xk.report.pojo.Vote;
import indi.xk.report.pojo.VoteRecord;
import indi.xk.report.pojo.dto.VoteDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author xk
 * @Date 2020/8/31 19:27
 * @Version 1.0
 */
@Repository
public interface VoteMapper {
    /**
     * 生成投票
     * @param vote
     */
    void addVote(VoteDTO vote);

    /**
     * 生成投票选项
     * @param vote
     */
    void addOption(VoteDTO vote);

    /**
     * 投票
     * @param vote
     * @return
     */
    int updateVote(VoteDTO vote);

    /**
     * 添加投票记录
     * @param vote
     */
    void addVoteRecord(VoteDTO vote);

    /**
     * 详情
     * @param id
     * @return
     */
    VoteDTO getVote(Integer id);

    /**
     * 查询投票记录
     * @param vote
     * @return
     */
    int queryVoteRecord(Vote vote);

    /**
     * 列表count
     * @return
     */
    Integer count();

    /**
     * 列表
     * @param map
     * @return
     */
    List<Vote> listVote(Map map);

    /**
     * 查询选项
     * @param id
     * @return
     */
    List<Option> getOptions(Integer id);

    /**
     * 查询投票记录
     * @param id
     * @return
     */
    List<VoteRecord> getVoteRecord(Integer id);
}
