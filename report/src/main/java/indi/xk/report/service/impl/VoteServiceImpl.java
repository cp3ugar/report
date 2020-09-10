package indi.xk.report.service.impl;

import indi.xk.report.mapper.VoteMapper;
import indi.xk.report.pojo.Option;
import indi.xk.report.pojo.Vote;
import indi.xk.report.pojo.VoteRecord;
import indi.xk.report.pojo.dto.VoteDTO;
import indi.xk.report.service.VoteService;
import indi.xk.report.utils.BaseRuntimeException;
import indi.xk.report.utils.PageView;
import indi.xk.report.utils.PagingUtil;
import indi.xk.report.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author xk
 * @Date 2020/8/31 19:25
 * @Version 1.0
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteMapper voteMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void generateVote(VoteDTO vote) {
        voteMapper.addVote(vote);
        voteMapper.addOption(vote);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VoteDTO vote(VoteDTO vote) {
        VoteDTO exit = voteMapper.getVote(vote.getId());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowTime = LocalDateTime.now().format(format);
        if(exit.getDeadline().compareTo(nowTime) < 0){
            throw new BaseRuntimeException(500,"该投票已截止");
        }
        if(exit.getNumber().equals(exit.getAmount())){
            throw new BaseRuntimeException(500,"该投票投票人数已满");
        }
        int res = voteMapper.queryVoteRecord(vote);
        if(res > 0){
            throw new BaseRuntimeException(500,"不能重复投票");
        }

        if(exit.getLimit() != null && exit.getLimit() < vote.getOptions().size()){
            throw new BaseRuntimeException(500,"该投票最多只能选择" + exit.getLimit() + "项");
        }
        voteMapper.updateVote(vote);
        voteMapper.addVoteRecord(vote);

        List<Option> options = voteMapper.getOptions(vote.getId());
        vote.setOptions(options);
        List<VoteRecord> recordList = voteMapper.getVoteRecord(vote.getId());
        Map data = recordList.stream().collect(Collectors.groupingBy(VoteRecord::getOptionId));
        vote.setVoteData(data);
        return vote;
    }

    @Override
    public VoteDTO getVote(Integer id) {
        VoteDTO vote = voteMapper.getVote(id);
        List<Option> options = voteMapper.getOptions(id);
        vote.setOptions(options);
        return vote;
    }

    @Override
    public PageView<Vote> listVote(PageView pageView) {
        Map map = new HashMap(1);
        Integer count = voteMapper.count();
        PagingUtil.pagingUtil(count, pageView);
        map.put("pageView", pageView);
        List<Vote> list = voteMapper.listVote(map);
        if (Utils.isEmpty(pageView)) {
            pageView = new PageView();
        }
        pageView.setRecords(list);
        return pageView;
    }
}
