package indi.xk.report.service.impl;

import indi.xk.report.mapper.StudentMapper;
import indi.xk.report.pojo.Ssbgxx;
import indi.xk.report.pojo.Ssbqxx;
import indi.xk.report.pojo.Ssxx;
import indi.xk.report.pojo.Student;
import indi.xk.report.pojo.dto.StudentDTO;
import indi.xk.report.service.StudentService;
import indi.xk.report.utils.BaseRuntimeException;
import indi.xk.report.utils.PageView;
import indi.xk.report.utils.PagingUtil;
import indi.xk.report.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xk
 * @Date 2019/12/18 15:12
 * @Version 1.0
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * list
     * @param
     * @return
     * @author xk
     * @date 2019/12/18  15:13
     */
    @Override
    public PageView<StudentDTO> findAll(PageView pageView) {
        Map map = new HashMap(1);
        Integer count = studentMapper.count();
        PagingUtil.pagingUtil(count, pageView);
        map.put("pageView", pageView);
        List<StudentDTO> list = studentMapper.findAll(map);
        if (Utils.isEmpty(pageView)) {
            pageView = new PageView();
        }
        pageView.setRecords(list);
        return pageView;
    }

    /**
     * 添加
     * @param
     * @return
     * @author xk
     * @date 2019/12/23  17:57
     */
    @Override
    public void addStudent(StudentDTO student) {
        int row = studentMapper.addStudent(student);
        if(row != 1){
            throw new BaseRuntimeException(500,"添加失败！");
        }
    }

    @Override
    public void add() {
        List<Ssbqxx> ssbqxx=new ArrayList<>();
        String bqid="2";
        String ssid="2";
        String bqjd="2";
        String bqlx="2";
        String bqfs="2";
        String 	sflhcf="2";
        String cqzt="2";
        String bqqsr="2";
        String bqccjz="2";
        String czzt	="2";
        String sfxf="2";
        String cfxfr="2";
        String jbfg="2";


        Ssbqxx ss=new Ssbqxx();
        ss.setBqid(bqid);
        ss.setSsid(ssid);
        ss.setBqjd(bqjd);
        ss.setBqlx(bqlx);
        ss.setBqfs(bqfs);
        ss.setSflhcf(sflhcf);
        ss.setCqzt(cqzt);
        ss.setBqqsr(bqqsr);
        ss.setBqccjz(bqccjz);
        ss.setCzzt(czzt);
        ss.setSfxf(sfxf);
        ss.setCfxfr(cfxfr);
        ss.setJbfg(jbfg);


        ssbqxx.add(ss);

        //批量保存
        if(Utils.isNotEmpty(ssbqxx)){
            studentMapper.batchInsert1(ssbqxx);
        }

    }

    @Override
    public void addbg() {
        List<Ssbgxx> Ssbgxx=new ArrayList<>();
        String bgid="1";
        String mc="1";
        String ybglx="1";
        String lxdh="1";
        String xdz="1";
        String bgid1="2";
        Ssbgxx ss=new Ssbgxx();
        ss.setBgid(bgid);
        ss.setMc(mc);
        ss.setYbglx(ybglx);
        ss.setLxdh(lxdh);
        ss.setXdz(xdz);

        Ssbgxx.add(ss);

        if(Utils.isNotEmpty(Ssbgxx)){
            studentMapper.batchInsert2(Ssbgxx);
        }
    }

    @Override
    public void addtz() {
        List<Ssxx> sx=new ArrayList<>();
        String id="1";
        String dfmc="1";
        String jkid="1";
        String jkrzjh="1";
        String slfg="1";
        String id2="2";
        BigDecimal bde= new BigDecimal(12345);
        Ssxx s=new Ssxx();
        s.setJkid(jkid);
        s.setId(id);
        s.setDfmc(dfmc);
        s.setJkrzjh(jkrzjh);
        s.setSlfg(slfg);
        s.setBde(bde);

        sx.add(s);

        if(Utils.isNotEmpty(sx)){
            studentMapper.batchInsert3(sx);
        }
    }
}
