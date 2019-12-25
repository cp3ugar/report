package indi.xk.report.controller;


import indi.xk.report.pojo.LitigationLoan;
import indi.xk.report.service.LitigationLoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zxy on 2019/12/24.
 */
@Controller
public class LitigationLoanController {

    @Autowired
    private LitigationLoanService litigationLoanService;
    @GetMapping("/listLitigationLoan")
     public ModelAndView listlitigationLoan(){
        ModelAndView mv=new ModelAndView();

        List<LitigationLoan> litigationLoans=litigationLoanService.findAllLitigationLoan();
        mv.addObject("litigationLoans",litigationLoans);
        mv.setViewName("listLitigation");
       return mv;
   }
   @ResponseBody
   @GetMapping("/listbank")
    public List listbank(){
         List<String> banklist=litigationLoanService.banklist();
       for (String lists :banklist) {
           System.out.println(lists);
       }

       return banklist;
   }
    @ResponseBody
    @GetMapping("/listnum")
    public List listnum(){
        List<String> numlist=litigationLoanService.numlist();
        return numlist;
    }
    @ResponseBody
    @RequestMapping(value = "listselect" ,method = RequestMethod.GET)
    public ModelAndView listSelsct(LitigationLoan litigationLoan){
        ModelAndView mv=new ModelAndView();
              String  jgmc=litigationLoan.getJgmc();
        System.out.println(jgmc);
//        mv.addObject("litigationLoans",litigationLoans);
//        mv.setViewName("listLitigation");
           return null;
    }

}

