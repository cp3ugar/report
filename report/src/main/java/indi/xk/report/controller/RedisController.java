package indi.xk.report.controller;

import indi.xk.report.pojo.Student;
import indi.xk.report.utils.RedisUtil;
import indi.xk.report.utils.ReturnObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author xk
 * @Date 2020/1/2 14:33
 * @Version 1.0
 */
@RestController
public class RedisController {
    /**
     * redis中存储的过期时间60s
     */
    private static int ExpireTime = 60;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("set")
    public ReturnObject redisSet(String key, String value){
        return new ReturnObject(0,"",0,redisUtil.set(key,value));
    }

    @RequestMapping("setStudent")
    public ReturnObject redisSetStudent(String key){
        Student student = new Student();
        student.setId(1);
        student.setSex("m");
        student.setName("张三");
        student.setAge(20);
        student.setBirthday(new Date().toString());
        return new ReturnObject(0,"",0,redisUtil.set(key,student,ExpireTime));
    }

    @RequestMapping("get")
    public ReturnObject redisGet(String key){
        return new ReturnObject(0,"",0,redisUtil.get(key));
    }

    @RequestMapping("expire")
    public ReturnObject expire(String key){
        return new ReturnObject(0,"",0,redisUtil.expire(key,ExpireTime));
    }
}
