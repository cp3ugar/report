package indi.xk.report.mapper;

import indi.xk.report.pojo.LitigationLoan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zxy on 2019/12/24.
 */
@Mapper
public interface LitigationLoanMapper {
    @Select("select * from litigation")
    List<LitigationLoan> findAllLitigationLoan();
    @Select("select jgmc from litigation")
    List <String>findbank();
    @Select("select laqs from litigation")
    List <String>findnum();
    @Select("select <if test='jgmc!=null and jgmc!='''> #{jgmc} </if> <if test=> ")
    LitigationLoan listselect();
}
