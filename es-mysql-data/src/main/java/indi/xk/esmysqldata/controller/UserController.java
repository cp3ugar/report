package indi.xk.esmysqldata.controller;

import indi.xk.esmysqldata.bean.User;
import indi.xk.esmysqldata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author xk
 * @Date 2020/5/14 15:29
 * @Version 1.0
 */
@RestController("/demo")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String findAll(){
        userService.findAll();
        return "all";
    }

    @PostMapping("/add")
    public String create(@RequestParam String name,@RequestParam String email,@RequestParam String tags){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setTags(tags);
        user.setLast_updated(System.currentTimeMillis());
        user.setIs_deleted(false);
        userService.save(user);
        return "add:id=" + user.getId();
    }

    @PutMapping("/update")
    public String update(@RequestParam Integer id,@RequestParam String name,@RequestParam String email,@RequestParam String tags){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setTags(tags);
        user.setLast_updated(System.currentTimeMillis());
        user.setIs_deleted(false);
        userService.save(user);
        return "update:id=" + id;
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Integer id){
        User user = userService.one(id);
        user.setLast_updated(System.currentTimeMillis());
        user.setIs_deleted(true);
        userService.save(user);
        return "delete:id=" + id;
    }
}
