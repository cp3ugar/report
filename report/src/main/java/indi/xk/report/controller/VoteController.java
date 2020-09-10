package indi.xk.report.controller;

import indi.xk.report.pojo.Option;
import indi.xk.report.pojo.Vote;
import indi.xk.report.pojo.dto.VoteDTO;
import indi.xk.report.pojo.validate.Validate4Add;
import indi.xk.report.pojo.validate.Validate4Update;
import indi.xk.report.service.VoteService;
import indi.xk.report.utils.BaseRuntimeException;
import indi.xk.report.utils.PageView;
import indi.xk.report.utils.ReturnObject;
import indi.xk.report.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author xk
 * @Date 2020/8/31 19:08
 * @Version 1.0
 */
@Controller
public class VoteController {
    @Autowired
    private VoteService voteService;

    @RequestMapping("/toVote")
    public String toVote(){
        return "jsp/vote";
    }

    @PostMapping("/generateVote")
    @ResponseBody
    public ReturnObject generateVote(@RequestBody @Validated(Validate4Add.class) VoteDTO vote){
        if(vote.getType().equals(2)){
            if(vote.getLimit() == null){
               throw new BaseRuntimeException(400,"请输入最多选几项");
            }
        }
        for(Option option : vote.getOptions()){
            if(Utils.isEmpty(option.getName())){
                throw new BaseRuntimeException(400,"选项不能为空");
            }
        }
        voteService.generateVote(vote);
        return ReturnObject.outSuccess("发起成功");
    }

    @PostMapping("/vote")
    @ResponseBody
    public ReturnObject vote(@RequestBody @Validated(Validate4Update.class)VoteDTO vote){
        ReturnObject ro = new ReturnObject();
        if(vote.getOptionId() == null && vote.getOptions().size() == 0){
            throw new BaseRuntimeException(400,"选项不能为空");
        }
        VoteDTO voteDTO = voteService.vote(vote);
        ro.setData(voteDTO);
        ro.setMsg("已投票");
        ro.setCode(0);
        return ro;
    }

    @GetMapping("/getVote/{id}")
    @ResponseBody
    public ReturnObject getVote(@PathVariable Integer id){
        ReturnObject ro = new ReturnObject();
        VoteDTO vote = voteService.getVote(id);
        ro.setData(vote);
        return ro;
    }

    @GetMapping("/listVote")
    @ResponseBody
    private ReturnObject listVote(@RequestParam(value = "page",defaultValue = "1")Integer pageNow,
                                  @RequestParam(value = "limit",defaultValue = "10")Integer pageSize){
        PageView pageView = new PageView(pageSize,pageNow);
        PageView<Vote> votes = voteService.listVote(pageView);
        return new ReturnObject(0,"",votes.getRowCount(),votes.getRecords());
    }
}
