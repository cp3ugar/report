package indi.xk.report.service;

import indi.xk.report.pojo.LitigationLoan;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zxy on 2019/12/24.
 */
@Component
public interface LitigationLoanService {
    List<LitigationLoan> findAllLitigationLoan();
    List<String> banklist();
    List<String> numlist();
    LitigationLoan listselect();
}
