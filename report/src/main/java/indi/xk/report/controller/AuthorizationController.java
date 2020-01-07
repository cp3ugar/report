package indi.xk.report.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zxy on 2020/1/3.
 */
@RestController
public class AuthorizationController {
    @GetMapping("/dog")
    public String dog(){

        Subject subject = SecurityUtils.getSubject();
        System.out.println( subject.hasRole("ROLE_DOG"));
        if(subject.hasRole("ROLE_DOG")){
            return "dog√";
        }
        else {
            return  "dog×";
        }
    }

    @GetMapping("/cat")
    public String cat(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("ROLE_CAT")){
            return "cat√";
        }
        else {
            return  "cat×";
        }
    }


    @GetMapping("/sing")
    @RequiresRoles("ROLE_CAT")
    public String sing(){
        return "sing";
    }
    @GetMapping("/jump")
    @RequiresPermissions("jump")
    public String jump(){
        return "jump";
    }
    @GetMapping("/rap")
    public String rap(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isPermitted("rap")){
            return "rap";
        }else{
            return "没权限你Rap个锤子啊!";
        }

    }
    @GetMapping("/basketball")
    public String basketball(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isPermitted("basketball")){
            return "basketball";
        }else{
            return "你会打个锤子啊!";
        }
    }
}
