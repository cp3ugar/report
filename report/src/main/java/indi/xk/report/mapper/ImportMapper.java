package indi.xk.report.mapper;

import indi.xk.report.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author xk
 * @Date 2019/12/27 10:30
 * @Version 1.0
 */
@Mapper
@Component
public interface ImportMapper {
    /**
     * 批量保存ssbgxx
     * @param ssbgxxs
     */
    void batchInsertSsbgxx(List<Ssbgxx> ssbgxxs);

    /**
     * 批量保存ssbqxx
     * @param ssbqxxs
     */
    void batchInsertSsbqxx(List<Ssbqxx> ssbqxxs);

    /**
     * 批量保存ssxx
     * @param ssxxs
     */
    void batchInsertSsxx(List<Ssxx> ssxxs);

    /**
     * 批量保存sszxxx
     * @param sszxxxs
     */
    void batchInsertSszxxx(List<Sszxxx> sszxxxs);

    /**
     * 批量保存sslaxx
     * @param sslaxxs
     */
    void batchInsertSslaxxs(List<Sslaxx> sslaxxs);

    /**
     * 查询所有诉讼管理机构
     * @return
     */
    List<Org> queryAllOrgs();
}
