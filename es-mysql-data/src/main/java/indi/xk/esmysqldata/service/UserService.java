package indi.xk.esmysqldata.service;

import indi.xk.esmysqldata.bean.User;
import indi.xk.esmysqldata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xk
 * @Date 2020/5/14 15:53
 * @Version 1.0
 */
@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public void findAll() {
        userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User one(Integer id){
        return userRepository.findById(id).get();
    }
}
