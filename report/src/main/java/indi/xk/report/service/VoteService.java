package indi.xk.report.service;

import indi.xk.report.pojo.Vote;
import indi.xk.report.pojo.dto.VoteDTO;
import indi.xk.report.utils.PageView;

/**
 * @Author xk
 * @Date 2020/8/31 19:24
 * @Version 1.0
 */
public interface VoteService {
    /**
     * 生成投票
     * @param vote
     */
    void generateVote(VoteDTO vote);

    /**
     * 投票
     * @param vote
     */
    VoteDTO vote(VoteDTO vote);

    /**
     * 详情
     * @param id
     * @return
     */
    VoteDTO getVote(Integer id);

    /**
     * 列表
     * @param pageView
     * @return
     */
    PageView<Vote> listVote(PageView pageView);
}
