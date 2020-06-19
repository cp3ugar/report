package indi.xk.report.service;


import indi.xk.report.pojo.Cat;

import java.util.List;

/**
 * @Author xk
 * @Date 2020/6/19 18:06
 * @Version 1.0
 */
public interface CatService {
    void addOneCat();

    void deleteOneCat(Integer id);

    Cat getOneCat(Integer id);

    void updateOneCat(Cat cat);

    List<Cat> getCats(String name, Integer type);
}
