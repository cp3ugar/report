package indi.xk.report.service.impl;


import indi.xk.report.pojo.Cat;
import indi.xk.report.service.CatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xk
 * @Date 2020/6/19 18:13
 * @Version 1.0
 */
@Service
public class CatServiceImpl implements CatService {
    @Override
    public void addOneCat() {

    }

    @Override
    public void deleteOneCat(Integer id) {

    }

    @Override
    public Cat getOneCat(Integer id) {
        Cat cat = Cat.builder()
                .id(1)
                .name("陈皮")
                .type(1)
                .nickName("皮皮").build();
        return cat;
    }

    @Override
    public void updateOneCat(Cat cat) {

    }

    @Override
    public List<Cat> getCats(String name, Integer type) {
        List<Cat> list = new ArrayList<>();
        Cat cat1 = Cat.builder()
                .id(1)
                .name("陈皮")
                .type(1)
                .nickName("皮皮").build();
        Cat cat2 = Cat.builder()
                .id(1)
                .name("坨坨")
                .type(1)
                .nickName("坨娃子").build();
        Cat cat3 = Cat.builder()
                .id(1)
                .name("孔孔")
                .type(1)
                .nickName("骑士").build();
        list.add(cat1);
        list.add(cat2);
        list.add(cat3);
        return list;
    }
}
