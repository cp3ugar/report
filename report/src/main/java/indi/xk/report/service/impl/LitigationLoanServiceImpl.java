package indi.xk.report.service.impl;

import indi.xk.report.mapper.LitigationLoanMapper;
import indi.xk.report.pojo.LitigationLoan;
import indi.xk.report.service.LitigationLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zxy on 2019/12/24.
 */
@Service
public class LitigationLoanServiceImpl implements LitigationLoanService {
    @Autowired
    private LitigationLoanMapper litigationLoanMapper;
    @Override
    public List<LitigationLoan> findAllLitigationLoan() {
        return litigationLoanMapper.findAllLitigationLoan();
    }

    @Override
    public List banklist() {
        return litigationLoanMapper.findbank();
    }

    @Override
    public List<String> numlist() {
        return litigationLoanMapper.findnum();
    }

    @Override
    public LitigationLoan listselect() {
        return null;
    }

}
