package indi.xk.report.controller;

import indi.xk.report.pojo.AjaxResponse;
import indi.xk.report.pojo.Cat;
import indi.xk.report.service.CatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author xk
 * @Date 2020/6/19 17:46
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/cats")
public class CatController {
    @Autowired
    private CatService catService;

    @PostMapping("/cats")
    public AjaxResponse addOneCat(@RequestBody Cat cat){
        catService.addOneCat();
        return AjaxResponse.success("添加成功");
    }

    @DeleteMapping("/cats/{id}")
    public AjaxResponse deleteOneCat(@PathVariable("id") Integer id){
        catService.deleteOneCat(id);
        return AjaxResponse.success("删除成功");
    }

    @GetMapping("/cats/{id}")
    public AjaxResponse getOneCat(@PathVariable("id") Integer id){
        Cat cat = catService.getOneCat(id);
        return AjaxResponse.success(cat);
    }

    @GetMapping("/cats")
    public AjaxResponse getCats(@RequestParam String name, @RequestParam Integer type){
        List<Cat> cats = catService.getCats(name,type);
        return AjaxResponse.success(cats);
    }

    @PutMapping("/cats")
    public AjaxResponse updateOneCat(@RequestBody Cat cat){
        catService.updateOneCat(cat);
        return AjaxResponse.success("更新成功");
    }
}
